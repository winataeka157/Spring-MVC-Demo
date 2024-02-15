package com.example.bliblispringdemo;

import com.example.bliblispringdemo.demo.ParkingManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class BlibliSpringDemoApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BlibliSpringDemoApplication.class);
		ParkingManager parkingManager = context.getBean(ParkingManager.class);
		parkingManager.displayInfo();
	}

}
