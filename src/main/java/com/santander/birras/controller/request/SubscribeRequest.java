package com.santander.birras.controller.request;

import io.swagger.annotations.ApiModelProperty;

public class SubscribeRequest {

    @ApiModelProperty(notes = "Id de la meetup a la cual quiero subscribirme")
    Long meetUpId;

    public Long getMeetUpId() {
        return meetUpId;
    }

    public void setMeetUpId(Long meetUpId) {
        this.meetUpId = meetUpId;
    }
}
