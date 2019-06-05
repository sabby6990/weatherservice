package com.weather.runner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.model.Response;
import com.weather.model.WeatherRequest;
import com.weather.util.FileUtil;
import com.weather.util.RestClient;

@RestController
public class WeatherServiceRest {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	RestClient<WeatherRequest, String> restClient;

	@Autowired
	Response response;

	@GetMapping("/pingWeather")
	public String sayHello() {
		return "Weather service is healthy!";
	}

	@PostConstruct
	void postInit() {
		response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/uploadCities")
	public Response uploadCities(@RequestParam("file") MultipartFile file) {
		LOGGER.debug("uploading cities ....");
		List<HttpStatus> jsonResponses = new ArrayList<>();

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				String content = new String(bytes);
				ObjectMapper mapper = new ObjectMapper();

				// convert JSON string to Map
				Map<String, Integer> map = mapper.readValue(content, Map.class);

				map.forEach((cities, ids) -> {
					UriComponentsBuilder queryParam = UriComponentsBuilder
							.fromHttpUrl("http://api.openweathermap.org/data/2.5/weather").queryParam("q", cities)
							.queryParam("appid", "aa69195559bd4f88d79f9aadeb77a8f6");
					ResponseEntity<String> sendGetRequest = restClient.sendGetRequest(String.class, queryParam);
					if (sendGetRequest.getStatusCode() == HttpStatus.OK) {
						try {
							FileUtil.createFile(cities, sendGetRequest.getBody());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					jsonResponses.add(sendGetRequest.getStatusCode());

				});
				if (jsonResponses.contains(HttpStatus.INTERNAL_SERVER_ERROR)) {
					LOGGER.error("one of the request to openweather Failed! ..");
					return response;
				} else {
					LOGGER.debug("upload and retrieve info successfull.. ");
					response.setStatusCode(HttpStatus.OK);
					response.setMessage("Sucessfully upladed and retrieved weather info");
					return response;
				}

			} catch (Exception e) {
				LOGGER.error("Exception occured",e);
				return response;
			}
		}

		return response;
	}

}
