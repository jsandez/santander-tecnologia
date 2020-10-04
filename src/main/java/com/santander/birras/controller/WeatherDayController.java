package com.santander.birras.controller;

import com.santander.birras.controller.response.GetWeatherOfDayResponse;
import com.santander.birras.service.WeatherDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/weather")
public class WeatherDayController {

    @Autowired
    private WeatherDayService weatherDayService;

    @GetMapping(value = "/{dateOfMeetup}")
    public ResponseEntity<GetWeatherOfDayResponse> getWeatherOfDay(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfMeetup) {
        GetWeatherOfDayResponse response;
        try {
            response = weatherDayService.getWeatherOfDay(dateOfMeetup);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(response,HttpStatus.OK);
    }
}
