package com.santander.birras.controller.request;

import io.swagger.annotations.ApiModelProperty;

public class CheckInRequest {

    @ApiModelProperty(notes = "Id de la meetup a la cual asististe")
    Long meetUpId;

    public Long getMeetUpId() {
        return meetUpId;
    }

    public void setMeetUpId(Long meetUpId) {
        this.meetUpId = meetUpId;
    }
}
