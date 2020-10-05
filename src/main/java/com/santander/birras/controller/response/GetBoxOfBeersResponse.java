package com.santander.birras.controller.response;

import io.swagger.annotations.ApiModelProperty;

public class GetBoxOfBeersResponse {

    @ApiModelProperty(notes = "Cantidad de cajas de birras que hay que comprar")
    private Integer boxOfBeers;

    public Integer getBoxOfBeers() {
        return boxOfBeers;
    }

    public void setBoxOfBeers(Integer boxOfBeers) {
        this.boxOfBeers = boxOfBeers;
    }
}
