package com.santander.birras.model.weather.factory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class WeatherDayFacytoryTests {

  @InjectMocks
  private WeatherDayFactory weatherDayFacytory;

  @Mock
  private ColdDay coldDay;

  @Mock
  private PerfectDay perfectDay;

  @Mock
  private HotDay hotDay;

  @Test
  public void testHotDay() {
    Day day = weatherDayFacytory.getFactoryDay(25);
    assertTrue(day instanceof HotDay);
  }

  @Test
  public void testPerfectDay() {
    Day day = weatherDayFacytory.getFactoryDay(23);
    assertTrue(day instanceof PerfectDay);
  }

  @Test
  public void testColdDay() {
    Day day = weatherDayFacytory.getFactoryDay(19);
    assertTrue(day instanceof ColdDay);
  }

}
