package com.weather.util;

import javax.annotation.PostConstruct;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


/**
 * @author saurabh 
 * This is the rest client used to connect to the backend Rest
 *         api's
 */
@Component
public class RestClient<R, T> {

	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	RestTemplate restTemplate;

	HttpHeaders headers;
	

	@PostConstruct
	public void init() {
		
		restTemplate = restTemplateBuilder.build();
		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create()
                .setMaxConnTotal(10)
                .setMaxConnPerRoute(5)
                .build());
		
		 // used for intercepting Rest Template for logging purposes
		 
		ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(
				httpComponentsClientHttpRequestFactory);
		restTemplate.setRequestFactory(factory);
		
		headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);
	}

	/**
	 * @param url
	 * @param request
	 * @param typeParameterClass
	 *            response class type
	 * @return
	 */
	public ResponseEntity<T> sendPostRequest(String url, R request, Class<T> typeParameterClass) {
		HttpEntity<R> requestBody = new HttpEntity<>(request, headers);
		ResponseEntity<T> postForEntity = restTemplate.postForEntity(url, requestBody, typeParameterClass);
		return postForEntity;

	}

	/**
	 * @param request
	 *            request class
	 * @param typeParameterClass
	 *            return class type
	 * @param builder
	 *            uri with query parameters
	 * @return
	 */
	public ResponseEntity<T> sendGetRequest(Class<T> typeParameterClass, UriComponentsBuilder builder) {
		ResponseEntity<T> exchange = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
				typeParameterClass);
		return exchange;

	}

}
