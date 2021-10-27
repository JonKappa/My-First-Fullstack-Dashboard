package com.codedifferently.dashboard.server.weather.models.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;
import java.util.Map;

@JsonIgnoreProperties
public class WeatherApiData {

    private String name;
    @JsonProperty("weather")
    private List<Weather> weather;
    private Map<String, Double> coord;
    private Map<String, Double> main;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Map<String, Double> getCoord() {
        return coord;
    }

    public void setCoord(Map<String, Double> coord) {
        this.coord = coord;
    }

    public Map<String, Double> getMain() {
        return main;
    }

    public void setMain(Map<String, Double> main) {
        this.main = main;
    }
}
