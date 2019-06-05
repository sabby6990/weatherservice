package com.weather.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.weather.util","com.weather.model","com.weather.runner"})
public class OpenWeatherAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenWeatherAppApplication.class, args);
	}

}
