package com.example.bliblispringdemo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingManager {

  @Autowired
  private ParkingLot parkingLot;

  public void displayInfo() {
    System.out.println("This is the parking manager");
    parkingLot.displayInfo();
  }
}
