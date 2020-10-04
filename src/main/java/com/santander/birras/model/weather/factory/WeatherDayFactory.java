package com.santander.birras.model.weather.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherDayFactory {

    public static final int COLD_DAY_MAX_WEATHER = 20;
    public static final int PERFECT_DAY_MAX_WEATHER = 24;

    @Autowired
    private ColdDay coldDay;

    @Autowired
    private PerfectDay perfectDay;

    @Autowired
    private HotDay hotDay;

    public Day getFactoryDay(Integer weather) {
        if (weather < COLD_DAY_MAX_WEATHER) {
            return coldDay;
        } else if (weather <= PERFECT_DAY_MAX_WEATHER) {
            return perfectDay;
        } else {
            return hotDay;
        }
    }
}
