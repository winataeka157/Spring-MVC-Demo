package com.example.bliblispringdemo.command.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllParkingLotCommandRequest {
  private int skip;
  private int limit;
}
