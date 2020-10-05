package com.santander.birras.controller.request;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

public class GetWeatherOfDayRequest {

    @ApiModelProperty(notes = "Dia de la meetup de la cual que quiero averiguar el clima")
    private LocalDate dayOfMeetUp;

    public LocalDate getDayOfMeetUp() {
        return dayOfMeetUp;
    }

    public void setDayOfMeetUp(LocalDate dayOfMeetUp) {
        this.dayOfMeetUp = dayOfMeetUp;
    }
}
