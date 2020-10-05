package com.santander.birras.service;

import com.santander.birras.controller.request.CreateMeetupRequest;
import com.santander.birras.controller.response.CreateMeetupResponse;
import com.santander.birras.exception.WeatherDayNotFoundException;
import com.santander.birras.model.MeetUp;
import com.santander.birras.model.WeatherDay;
import com.santander.birras.model.weather.factory.ColdDay;
import com.santander.birras.model.weather.factory.Day;
import com.santander.birras.model.weather.factory.WeatherDayFactory;
import com.santander.birras.repository.MeetUpRepository;
import com.santander.birras.repository.MeetUpUsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MeetUpServiceTests {


    @InjectMocks
    private MeetUpServiceImpl meetUpService;

    @Mock
    private MeetUpRepository meetUpRepository;

    @Mock
    private MeetUpUsersRepository meetUpUsersRepository;

    @Mock
    private WeatherDayFactory weatherDayFactory;

    @Mock
    private WeatherDayService weatherDayService;

    @Test
    public void creatMeetUpTest() throws WeatherDayNotFoundException {
        WeatherDay weatherDay = new WeatherDay();
        weatherDay.setId(1L);
        weatherDay.setDay(LocalDate.now());
        weatherDay.setWeather(20);

        Day coldDay = new ColdDay();

        List<String> users = new ArrayList<String>();
        users.add("Jorge");
        users.add("Maria");

        when(weatherDayService.getWeatherDay(any())).thenReturn(weatherDay);
        when(weatherDayFactory.getFactoryDay(any())).thenReturn(coldDay);
        when(meetUpUsersRepository.getUsersFromMeetUp(any())).thenReturn(users);

        CreateMeetupRequest request = new CreateMeetupRequest();
        request.setDateMeetup(LocalDate.now());
        request.setUsers(users);

        CreateMeetupResponse response = meetUpService.createMeetup(request);


        final ArgumentCaptor<MeetUp> captor = ArgumentCaptor.forClass(MeetUp.class);
        Mockito.verify(meetUpRepository).save(captor.capture());
        MeetUp meetUp = captor.getValue();

        assertEquals(meetUp.getBoxOfBeers(),1);

        assertEquals(response.getUsers(), users);
    }
}
