package com.codedifferently.dashboard.server.weather.repos;

import com.codedifferently.dashboard.server.weather.models.WeatherEntity;
import org.springframework.data.repository.CrudRepository;

public interface WeatherEntityRepo extends CrudRepository<WeatherEntity,Long> {
    WeatherEntity findFirstByZipCode(String zipCode);
}
