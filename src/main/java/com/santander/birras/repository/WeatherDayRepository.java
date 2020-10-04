package com.santander.birras.repository;

import com.santander.birras.model.WeatherDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface WeatherDayRepository extends JpaRepository<WeatherDay,Long> {

    WeatherDay findByDay(LocalDate dayOfMeetup);

}
