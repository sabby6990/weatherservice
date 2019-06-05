package com.weather.util;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author saurabh 
 * File writed related utils
 * 
 */
public class FileUtil {
	
	public static final String OUTPUTFOLDER="/OutputFolder/";
	
	public final static String rootPath = System.getProperty("user.dir");
	
    public static void createFile(String city,String content) throws IOException{
 	   Date date = new Date();  
 	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
 	    String strDate= formatter.format(date);  
 	    String filePathName =rootPath+OUTPUTFOLDER+""+city+strDate+".json";
 	    writeToFile(content, filePathName);
 }
 
 public static void writeToFile(String stringToWrite, String outputFile) {
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
