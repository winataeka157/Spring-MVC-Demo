package com.example.bliblispringdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingLotEntity {

  private String id;
  private boolean isOccupied;
  private Vehicle vehicle;

  public static class Vehicle {
    private String registrationNumber;
    private String color;
  }
}
