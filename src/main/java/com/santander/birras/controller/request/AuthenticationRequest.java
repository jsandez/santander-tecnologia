package com.santander.birras.controller.request;

import io.swagger.annotations.ApiModelProperty;

public class AuthenticationRequest {

    @ApiModelProperty(notes = "Login del usuario")
    private String username;
    @ApiModelProperty(notes = "Password del usuario")
    private String password;

    public AuthenticationRequest() {}

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
}
