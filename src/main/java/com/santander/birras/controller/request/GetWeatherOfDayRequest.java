package com.santander.birras.controller.request;

import java.time.LocalDate;

public class GetWeatherOfDayRequest {

    private LocalDate dayOfMeetUp;

    public LocalDate getDayOfMeetUp() {
        return dayOfMeetUp;
    }

    public void setDayOfMeetUp(LocalDate dayOfMeetUp) {
        this.dayOfMeetUp = dayOfMeetUp;
    }
}
