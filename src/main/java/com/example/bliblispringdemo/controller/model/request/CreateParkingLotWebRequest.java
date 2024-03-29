package com.example.bliblispringdemo.controller.model.request;

import com.example.bliblispringdemo.validator.annotation.ValidParkingCount;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateParkingLotWebRequest {

  @ValidParkingCount
  @NotNull
  private Integer count;

}
