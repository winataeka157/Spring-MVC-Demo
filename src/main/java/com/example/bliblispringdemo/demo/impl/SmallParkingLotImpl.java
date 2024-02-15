package com.example.bliblispringdemo.demo.impl;

import com.example.bliblispringdemo.demo.ParkingLot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SmallParkingLotImpl implements ParkingLot {

  @Value("${parking.small-lot-capacity}")
  private int capacity;

  @Override
  public void displayInfo() {
    System.out.println("Small parking lot with capacity:" + capacity);
  }

}
