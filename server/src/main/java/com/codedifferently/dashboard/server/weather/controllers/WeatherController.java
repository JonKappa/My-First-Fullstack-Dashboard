package com.codedifferently.dashboard.server.weather.controllers;

import com.codedifferently.dashboard.server.weather.models.WeatherEntity;
import com.codedifferently.dashboard.server.weather.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping("/{zipcode}")
    public ResponseEntity<WeatherEntity> requestByZipCode(@PathVariable String zipcode){
        WeatherEntity entity = weatherService.currentWeather(zipcode);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
}
