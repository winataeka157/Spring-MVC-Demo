package com.example.bliblispringdemo.demo.impl;

import com.example.bliblispringdemo.demo.ParkingLot;
import org.springframework.stereotype.Component;

@Component
public class SmallParkingLotImpl implements ParkingLot {

  @Override
  public void displayInfo() {
    System.out.println("Small parking lot");
  }

}
