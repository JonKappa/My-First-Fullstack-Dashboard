package com.codedifferently.dashboard.server.weather.models.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;
import java.util.Map;

@JsonIgnoreProperties
public class WeatherApiData {

    public String name;
    @JsonProperty("weather")
    public List<Weather> weather;
    public Map<String, Double> coord;
    public Map<String, Double> main;

}
