package com.santander.birras.controller.response;

import io.swagger.annotations.ApiModelProperty;

public class AuthenticationResponse {

    @ApiModelProperty(notes = "JWT Token del usuario")
    private String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
