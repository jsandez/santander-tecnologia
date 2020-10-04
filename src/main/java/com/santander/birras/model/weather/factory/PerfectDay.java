package com.santander.birras.model.weather.factory;

import org.springframework.stereotype.Component;

@Component
public class PerfectDay implements Day{

    public static final float BEERS_BY_PERSON = 1.00f;

    @Override
    public Float beerByPerson() {
        return BEERS_BY_PERSON;
    }
}
