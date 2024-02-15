package com.example.bliblispringdemo.demo.impl;

import com.example.bliblispringdemo.demo.ParkingLot;
import org.springframework.stereotype.Component;

@Component
public class LargeParkingLotImpl implements ParkingLot {

  @Override
  public void displayInfo() {
    System.out.println("Large parking lot");
  }

}
