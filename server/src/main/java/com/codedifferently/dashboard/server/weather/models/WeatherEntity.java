package com.codedifferently.dashboard.server.weather.models;

import com.codedifferently.dashboard.server.weather.models.api.WeatherApiData;

import javax.persistence.*;
import java.util.Date;

@Entity
public class WeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String zipCode;
    private String description;
    private Double temp;
    private Double feelsLike;
    private Double tempMin;
    private Double tempMax;
    private String name;
    private Double lon;
    private Double lat;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    public WeatherEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }


    @PrePersist
    protected void onCreate() {
        Date date = new Date();
        created = date;
        updated = date;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    @Override
    public String toString() {
        return "WeatherEntity{" +
                ", zipCode='" + zipCode + '\'' +
                ", description='" + description + '\'' +
                ", temp=" + temp +
                ", feelsLike=" + feelsLike +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                ", name='" + name + '\'' +
                ", lon=" + lon +
                ", lat=" + lat +
                '}';
    }

    public static final WeatherEntity builder(WeatherApiData apiData){
        WeatherEntity entity = new WeatherEntity();
        entity.setDescription(apiData.getWeather().get(0).getDescription());
        entity.setFeelsLike(apiData.getMain().get("feels_like"));
        entity.setTemp(apiData.getMain().get("temp"));
        entity.setTempMin(apiData.getMain().get("temp_min"));
        entity.setTempMax(apiData.getMain().get("temp_max"));
        entity.setName(apiData.getName());
        entity.setLat(apiData.getCoord().get("lat"));
        entity.setLon(apiData.getCoord().get("lon"));
        return entity;
    }
}
