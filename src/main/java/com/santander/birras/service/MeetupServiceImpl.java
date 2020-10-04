package com.santander.birras.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.santander.birras.controller.request.CreateMeetupRequest;
import com.santander.birras.controller.response.CreateMeetupResponse;
import com.santander.birras.exception.MeetUpNotExist;
import com.santander.birras.exception.WeatherDayNotFoundException;
import com.santander.birras.model.MeetUp;
import com.santander.birras.model.WeatherDay;
import com.santander.birras.model.weather.factory.WeatherDayFactory;
import com.santander.birras.repository.MeetUpRepository;
import com.santander.birras.repository.MeetUpUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("meetUpService")
public class MeetupServiceImpl implements MeetUpService{

    public static Float BEERS_BY_BOX = 6.00f;

    @Autowired
    private MeetUpRepository meetUpRepository;

    @Autowired
    private MeetUpUsersRepository meetUpUsersRepository;

    @Autowired
    private WeatherDayFactory weatherDayFactory;

    @Autowired
    private WeatherDayService weatherDayService;

    @Override
    public CreateMeetupResponse createMeetup(CreateMeetupRequest createMeetupRequest) {
        MeetUp meetUp = new MeetUp();
        WeatherDay weatherDay;
        try {
            weatherDay = weatherDayService.getWeatherDay(createMeetupRequest.getDateMeetup());
        } catch (WeatherDayNotFoundException e) {
            try {
                weatherDay = weatherDayService.createWeatherDay(createMeetupRequest.getDateMeetup());
            } catch (JsonProcessingException jpe) {
                throw new RuntimeException("Error parseando json del cliente.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error interno creando la meetup.");
        }
        meetUp.setMeetUpDay(weatherDay);
        Integer boxOfBeers = calculateBoxOfBeers(createMeetupRequest.getUsers().size(), weatherDay.getWeather());
        meetUp.setBoxOfBeers(boxOfBeers);
        meetUpRepository.save(meetUp);
        CreateMeetupResponse response = new CreateMeetupResponse();
        response.setId(meetUp.getId());
        response.setUsers(meetUpUsersRepository.getUsersFromMeetUp(meetUp.getId()));
        return response;
    }

    @Override
    public List<MeetUp> getAllMeetups() {
        return meetUpRepository.findAll();
    }

    @Override
    public MeetUp getMeetup(Long id) {
        return meetUpRepository.findById(id).orElse(null);
    }

    @Override
    public void validateMeetup(Long id) throws MeetUpNotExist {
        if (getMeetup(id) == null) {
            throw new MeetUpNotExist("La meetup con id " + id + " no existe");
        }
    }

    private Integer calculateBoxOfBeers(Integer usersSize,
                                        Integer weatherOfDay) {
        Float amountOfBeers = usersSize.floatValue() *
                weatherDayFactory.
                        getFactoryDay(weatherOfDay).
                        beerByPerson();
        Integer boxOfBeers = ((Double) Math.floor(amountOfBeers/BEERS_BY_BOX)).intValue() + 1;
        return boxOfBeers;
    }

}
