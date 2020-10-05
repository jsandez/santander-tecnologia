package com.santander.birras.controller.request;

import io.swagger.annotations.ApiModelProperty;

public class CreateUserRequest {

    @ApiModelProperty(notes = "Username del usuario a crear")
    String username;
    @ApiModelProperty(notes = "Password del usuario a crear")
    String password;
    @ApiModelProperty(notes = "Rol del usuario a crear")
    String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
