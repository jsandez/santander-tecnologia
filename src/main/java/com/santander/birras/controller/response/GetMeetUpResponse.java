package com.santander.birras.controller.response;

import com.santander.birras.model.WeatherDay;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class GetMeetUpResponse {

    @ApiModelProperty(notes = "Id de la meetup")
    private Long id;
    @ApiModelProperty(notes = "Fecha y clima de la meetup")
    private WeatherDay weatherDay;
    @ApiModelProperty(notes = "Usuarios que forman parte de la meetup")
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

