package com.santander.birras.controller.request;

public class GetAmountOfBeersRequest {

    private Long meetUpId;

    public Long getMeetUpId() {
        return meetUpId;
    }

    public void setMeetUpId(Long meetUpId) {
        this.meetUpId = meetUpId;
    }
}
