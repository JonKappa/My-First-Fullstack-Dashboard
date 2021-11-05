package com.codedifferently.dashboard.server;

import com.codedifferently.dashboard.server.weather.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // scans and configures the component
public class ServerApplication {

	@Autowired
	WeatherService weatherService;

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
