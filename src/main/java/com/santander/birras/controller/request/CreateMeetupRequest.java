package com.santander.birras.controller.request;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.List;

public class CreateMeetupRequest {

    @ApiModelProperty(notes = "Dia que se realizara la meetup")
    private LocalDate dateMeetup;
    @ApiModelProperty(notes = "Usuarios que van a ser invitados")
    private List<String> users;

    public LocalDate getDateMeetup() {
        return dateMeetup;
    }

    public void setDateMeetup(LocalDate dateMeetup) {
        this.dateMeetup = dateMeetup;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
