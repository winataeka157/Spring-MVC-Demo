package com.example.bliblispringdemo.demo.impl;

import com.example.bliblispringdemo.demo.ParkingLot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class LargeParkingLotImpl implements ParkingLot {

  @Value("${parking.large-lot-capacity}")
  private int capacity;

  @Override
  public void displayInfo() {
    System.out.println("Large parking lot with capacity: " + capacity);
  }

}
