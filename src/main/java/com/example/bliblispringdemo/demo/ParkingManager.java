package com.example.bliblispringdemo.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParkingManager {

  private final ParkingLot parkingLot;

  public void displayInfo() {
    System.out.println("This is the parking manager");
    parkingLot.displayInfo();
  }
}
