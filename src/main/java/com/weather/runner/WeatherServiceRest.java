package com.weather.runner;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class WeatherServiceRest {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello Java Code Geeks!";
    }
    
    
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
    	
    	   if (!file.isEmpty()) { 
    	        try { 
    	            byte[] bytes = file.getBytes();     
    	            
    	            String content = new String(bytes);
    	            
    	            // Creating the directory to store file 
//    	            String rootPath = System.getProperty("catalina.home"); 
//    	            File dir = new File(rootPath + File.separator + "tmpFiles"); 
//    	            if (!dir.exists()) 
//    	                dir.mkdirs();     
//    	            // Create the file on server 
//    	            File serverFile = new File(dir.getAbsolutePath() + File.separator + "_1"); 
//    	            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile)); 
//    	            stream.write(bytes);
//    	            stream.close(); 
//    	            System.out.println("Server File Location=" + serverFile.getAbsolutePath());
    	            return content; 
    	        } catch (Exception e) { 
    	            return null; 
    	        } 
    	    }
		return null;
    }
    
}
