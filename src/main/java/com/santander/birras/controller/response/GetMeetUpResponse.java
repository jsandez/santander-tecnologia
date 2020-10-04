package com.santander.birras.controller.response;

import com.santander.birras.model.MeetUp;
import com.santander.birras.model.WeatherDay;

import java.util.List;

public class GetMeetUpResponse {

    private Long id;
    private WeatherDay weatherDay;
    private List<String> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WeatherDay getWeatherDay() {
        return weatherDay;
    }

    public void setWeatherDay(WeatherDay weatherDay) {
        this.weatherDay = weatherDay;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}

