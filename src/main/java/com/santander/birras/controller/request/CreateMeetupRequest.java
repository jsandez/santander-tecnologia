package com.santander.birras.controller.request;

import java.time.LocalDate;
import java.util.List;

public class CreateMeetupRequest {

    private LocalDate dateMeetup;
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
