package com.santander.birras.controller.response;

import java.util.List;

public class CreateMeetupResponse {

    private Long id;
    private List<String> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
