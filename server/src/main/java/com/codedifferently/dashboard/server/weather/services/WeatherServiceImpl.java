package com.codedifferently.dashboard.server.weather.services;

import com.codedifferently.dashboard.server.weather.models.WeatherEntity;
import com.codedifferently.dashboard.server.weather.models.api.WeatherApiData;
import com.codedifferently.dashboard.server.weather.repos.WeatherEntityRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class WeatherServiceImpl implements WeatherService{

    private static final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String api_url;

    @Value("${weather.api.units}")
    private String units;

    private RestTemplate restTemplate;
    private WeatherEntityRepo repo;

    @Autowired
    public WeatherServiceImpl(RestTemplate restTemplate, WeatherEntityRepo repo){
        this.restTemplate = restTemplate;
        this.repo = repo;
    }

    @Override
    public WeatherEntity findByZipCode(String zipCode) {
        return repo.findFirstByZipCode(zipCode);
    }

    @Override
    public WeatherEntity currentWeather(String zipcode) {
        WeatherEntity wEntity = findByZipCode(zipcode);
        if(wEntity == null){
            wEntity = requestFreshData(zipcode);
        } else if (greaterThanMaxFiveMins(wEntity)){
            logger.debug("Data is older than five mins");
            repo.delete(wEntity);
            wEntity = requestFreshData(zipcode);
        }else {
            logger.debug("Using current data from database with timestamp {}", wEntity.getUpdated().toString());
        }
        return wEntity;
    }

    private WeatherEntity requestFreshData(String zipcode){
        logger.info("Requesting Fresh data from external api for zipcode: {}", zipcode);
        WeatherEntity wEntity = null;
        String apiUrl = String.format("%s?zip=%s&appid=%s&units=%s", api_url, zipcode,apiKey,units);
        logger.debug("Making Get API request to: {}",apiUrl);
        WeatherApiData data = restTemplate.getForObject(apiUrl, WeatherApiData.class);
        wEntity= WeatherEntity.builder(data);
        wEntity.setZipCode(zipcode);
        return repo.save(wEntity);
    }

    private boolean greaterThanMaxFiveMins(WeatherEntity entity){
        Date currentDate = new Date();
        Date previousDate = entity.getUpdated();
        long difference = Math.abs(currentDate.getTime() - previousDate.getTime());
        return (difference > 10 * 60 * 1000);
    }


}
