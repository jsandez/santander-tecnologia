package com.santander.birras.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MeetUp {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private WeatherDay weatherDay;
    private Integer boxOfBeers;

    public MeetUp() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WeatherDay getMeetUpDay() {
        return weatherDay;
    }

    public void setMeetUpDay(WeatherDay weatherDay) {
        this.weatherDay = weatherDay;
    }

    public Integer getBoxOfBeers() {
        return boxOfBeers;
    }

    public void setBoxOfBeers(Integer amountOfBeers) {
        this.boxOfBeers = amountOfBeers;
    }
}
