package com.santander.birras.controller.request;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class InviteUsersRequest {

    @ApiModelProperty(notes = "Id de la meetup a la cual quiero invitar usuarios")
    Long meetUpId;
    @ApiModelProperty(notes = "Usuarios invitados")
    List<String> users;

    public Long getMeetUpId() {
        return meetUpId;
    }

    public void setMeetUpId(Long meetUpId) {
        this.meetUpId = meetUpId;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
