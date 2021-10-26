package com.codedifferently.dashboard.server.weather.services;

import com.codedifferently.dashboard.server.weather.models.WeatherEntity;

public interface WeatherService {
    WeatherEntity currentWeather(String zipCode);
    WeatherEntity findByZipCode(String zipCode);
}
