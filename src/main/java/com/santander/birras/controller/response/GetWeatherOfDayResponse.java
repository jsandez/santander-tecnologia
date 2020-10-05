package com.santander.birras.controller.response;

import io.swagger.annotations.ApiModelProperty;

public class GetWeatherOfDayResponse {

    @ApiModelProperty(notes = "Temperatura de la meetup")
    private Integer weather;

    public Integer getWeather() {
        return weather;
    }

    public void setWeather(Integer weather) {
        this.weather = weather;
    }
}
