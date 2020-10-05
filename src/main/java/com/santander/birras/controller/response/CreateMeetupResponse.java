package com.santander.birras.controller.response;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class CreateMeetupResponse {

    @ApiModelProperty(notes = "Id de la meetup creada")
    private Long id;
    @ApiModelProperty(notes = "Usuarios que forman parte de la meetup")
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
