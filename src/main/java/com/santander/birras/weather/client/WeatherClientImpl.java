package com.santander.birras.weather.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.ws.Response;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class WeatherClientImpl implements WeatherClient {

    @Override
    public Integer getWeatherOfDay(LocalDate localDate) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String requestString ="https://community-open-weather-map.p.rapidapi.com/forecast?q=buenos aires&units=metric";
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com");
        headers.add("x-rapidapi-key", "34d6a34cfdmsh0dacc5516e799edp15f681jsn35ea7021fdb6");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> response
                = restTemplate.exchange(requestString, HttpMethod.GET, httpEntity, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode list = root.path("list");
        for (JsonNode node: list) {
            LocalDateTime dateOfNode = new Timestamp(node.path("dt").asLong() * 1000).toLocalDateTime();
            if ((localDate.atStartOfDay()).isEqual(dateOfNode.toLocalDate().atStartOfDay())) {
                return (int) Math.floor(node.path("main").path("temp").asDouble());            }
        }
        // ACA SE PUEDE LLEGAR SI PEDIMOS UN DIA QUE NO ESTA ENTRE EL DIA DE HOY + 5
        return 0;
    }
}
