package com.weather.runner;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.model.Cities;

@RestController
public class WeatherServiceRest {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Java Code Geeks!";
    }
    
    
    @PostMapping("/uploadCities")
    public String uploadCities(@RequestParam("file") MultipartFile file) {
    	
    	   if (!file.isEmpty()) { 
    	        try { 
    	            byte[] bytes = file.getBytes();     
    	            
    	            String content = new String(bytes);
    	            	
    	            ObjectMapper mapper = new ObjectMapper();
    	            
    	            // convert JSON string to Map
    	            Map<String, Integer> map = mapper.readValue(content, Map.class);
    	            
    	            map.forEach((cities,ids) -> {
    	            	System.out.println(cities + " = "+ ids );
    	            });
    	            return content; 
    	        } catch (Exception e) { 
    	            return null; 
    	        } 
    	    }
		return null;
    }
    
}
