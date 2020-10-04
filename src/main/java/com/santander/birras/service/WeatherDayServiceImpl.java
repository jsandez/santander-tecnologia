package com.santander.birras.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.santander.birras.controller.response.GetWeatherOfDayResponse;
import com.santander.birras.exception.WeatherDayNotFoundException;
import com.santander.birras.model.WeatherDay;
import com.santander.birras.repository.WeatherDayRepository;
import com.santander.birras.weather.client.WeatherClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("meetUpDayService")
public class WeatherDayServiceImpl implements WeatherDayService {

    @Autowired
    private WeatherDayRepository weatherDayRepository;

    @Autowired
    private WeatherClient weatherClient;

    @Override
    public GetWeatherOfDayResponse getWeatherOfDay(LocalDate day) {
        GetWeatherOfDayResponse response = new GetWeatherOfDayResponse();
        WeatherDay weatherDay;
        Integer weather;
        try {
            weatherDay = getWeatherDay(day);
        } catch (WeatherDayNotFoundException e) {
            try {
                weatherDay = createWeatherDay(day);
            } catch (JsonProcessingException jpe) {
                throw new RuntimeException("Error parseando json del cliente.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error interno del servidor obteniendo la temperatura del dia.");
        }
        weather = weatherDay.getWeather();
        response.setWeather(weather);
        return response;
    }

    @Override
    public WeatherDay getWeatherDay(LocalDate dayOfMeetup) throws WeatherDayNotFoundException {
        WeatherDay weatherDay = weatherDayRepository.findByDay(dayOfMeetup);
        if (weatherDay == null) {
            throw new WeatherDayNotFoundException("Day not found");
        }
        return weatherDay;
    }

    @Override
    public WeatherDay createWeatherDay(LocalDate dayOfMeetup) throws JsonProcessingException {
        WeatherDay weatherDay = new WeatherDay();
        weatherDay.setDay(dayOfMeetup);
        weatherDay.setWeather(weatherClient.getWeatherOfDay(dayOfMeetup));
        weatherDayRepository.save(weatherDay);
        return weatherDay;
    }

}
