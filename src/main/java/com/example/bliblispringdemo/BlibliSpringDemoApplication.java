package com.example.bliblispringdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.example.bliblispringdemo.property.ParkingProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {ParkingProperties.class})
public class BlibliSpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlibliSpringDemoApplication.class, args);
	}

}
