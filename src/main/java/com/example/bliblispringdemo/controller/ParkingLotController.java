package com.example.bliblispringdemo.controller;

import com.example.bliblispringdemo.base.CommandLocator;
import com.example.bliblispringdemo.controller.model.request.CreateParkingLotWebRequest;
import com.example.bliblispringdemo.controller.model.response.BaseResponse;
import com.example.bliblispringdemo.controller.model.response.ParkingLotWebResponse;
import com.example.bliblispringdemo.command.CreateParkingLotCommand;
import com.example.bliblispringdemo.command.model.CreateParkingLotCommandRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking-lots")
@RequiredArgsConstructor
public class ParkingLotController {

  private final CommandLocator commandLocator;

  @PostMapping
  public BaseResponse<List<ParkingLotWebResponse>> createParkingLots(
    @RequestBody CreateParkingLotWebRequest createParkingLotWebRequest) {
    return BaseResponse.ok(commandLocator.getCommand(CreateParkingLotCommand.class)
      .execute(CreateParkingLotCommandRequest.builder()
        .count(createParkingLotWebRequest.getCount())
        .build()));
  }

}
