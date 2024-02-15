package com.example.bliblispringdemo;

import com.example.bliblispringdemo.demo.ParkingManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class BlibliSpringDemoApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(BlibliSpringDemoApplication.class, args);
		ParkingManager parkingManager = context.getBean(ParkingManager.class);
		parkingManager.displayInfo();
	}

}
