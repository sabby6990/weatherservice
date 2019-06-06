package com.weather.runner;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.weather.util.FileUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenWeatherAppApplicationTests {

	@Mock
	FileUtil fileUtil;
	
	@Test
	public void contextLoads() {
		System.out.println("this is a test unit test");
	}
	
	@Test
	public void testSendGet() throws IOException{

	}

}
