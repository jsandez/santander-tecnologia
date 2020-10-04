package com.santander.birras.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.santander.birras.controller.response.GetWeatherOfDayResponse;
import com.santander.birras.exception.WeatherDayNotFoundException;
import com.santander.birras.model.WeatherDay;

import java.time.LocalDate;

public interface WeatherDayService {

    GetWeatherOfDayResponse getWeatherOfDay(LocalDate day);
    WeatherDay getWeatherDay(LocalDate dayOfMeetup) throws WeatherDayNotFoundException;
    WeatherDay createWeatherDay(LocalDate dayOfMeetup) throws JsonProcessingException;
}
