package com.santander.birras.weather.client;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.LocalDate;

public interface WeatherClient {

    public Integer getWeatherOfDay(LocalDate localDate) throws JsonProcessingException;

}
