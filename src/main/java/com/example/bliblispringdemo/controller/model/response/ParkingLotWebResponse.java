package com.example.bliblispringdemo.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingLotWebResponse {

  private String id;
  private boolean isOccupied;
  private Vehicle vehicle;

  public static class Vehicle {
    private String registrationNumber;
    private String color;
  }

}
