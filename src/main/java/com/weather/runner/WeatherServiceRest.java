package com.weather.runner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.weather.model.WeatherRequest;
import com.weather.restutil.RestClient;

@RestController
public class WeatherServiceRest {
	
	public static final String OUTPUTFOLDER="/OutputFolder/";
	
	@Autowired
	RestClient<WeatherRequest,String> restClient;
	
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Java Code Geeks!";
    }
    
    
    @PostMapping("/uploadCities")
    public List<String> uploadCities(@RequestParam("file") MultipartFile file) {
    	List<String> jsonResponses = new ArrayList<>();
    	
    	StringBuffer fileNameBuffer = new StringBuffer();
    	
    	
    	String fileName="";
    	String rootPath = System.getProperty("user.dir");
    	   if (!file.isEmpty()) { 
    	        try { 
    	            byte[] bytes = file.getBytes();     
    	            String content = new String(bytes);
    	            ObjectMapper mapper = new ObjectMapper();
    	            
    	            // convert JSON string to Map
    	            Map<String, Integer> map = mapper.readValue(content, Map.class);
    	            
    	    		UriComponentsBuilder queryParam = UriComponentsBuilder.fromHttpUrl("http://api.openweathermap.org/data/2.5/weather")
    	    		        .queryParam("appid","aa69195559bd4f88d79f9aadeb77a8f6");
    	    		
    	            map.forEach((cities,ids) -> {
    	            	System.out.println(cities + " = "+ ids );
    	            	WeatherRequest requestobject = new WeatherRequest();
    	            	requestobject.setQ(cities);
    	            	requestobject.setAppid("aa69195559bd4f88d79f9aadeb77a8f6");
    	            	queryParam.queryParam("q", cities);
    	            	
    	            	ResponseEntity<String> sendGetRequest = restClient.sendGetRequest(requestobject, String.class, queryParam);
    	            	
    	            	if(sendGetRequest.getStatusCode()==HttpStatus.OK)
    	            	{
    	            		try {
								createFile(rootPath,cities,sendGetRequest.getBody());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
    	            	}
    	            	jsonResponses.add(sendGetRequest.getBody());
    	            	
    	            });
    	            
    	            
    	            fileNameBuffer.append(System.getProperty("user.dir"));
    	            fileNameBuffer.append(OUTPUTFOLDER);
    	            
    	            


	            	
    	            return jsonResponses; 
    	        } catch (Exception e) { 
    	        	e.printStackTrace();
    	            return null; 
    	        } 
    	    }
		return null;
    }
    
    
    private static void createFile(String rootPath,String city,String content) throws IOException{
    	   Date date = new Date();  
    	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
    	    String strDate= formatter.format(date);  
    	
    	String filePathName =rootPath+OUTPUTFOLDER+""+city+strDate+".json";
    	File file1 = new File(filePathName);
    	file1.createNewFile();
    	
    	writeToFile(content, filePathName);
    }
    
    private static void writeToFile(String stringToWrite, String outputFile) {
    	try {       
    	    FileWriter writer = new FileWriter(outputFile);
    	    writer.append(stringToWrite);
    	    writer.flush();
    	    writer.close();
    	} catch (Exception exp) {
    	   exp.printStackTrace();
    	}
    	}
    
}
