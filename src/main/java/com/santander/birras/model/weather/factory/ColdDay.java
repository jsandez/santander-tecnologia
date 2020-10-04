package com.santander.birras.model.weather.factory;

import org.springframework.stereotype.Component;

@Component
public class ColdDay implements Day{

    public static final float BEERS_BY_PERSON = 0.75f;

    @Override
    public Float beerByPerson() {
        return BEERS_BY_PERSON;
    }
}
