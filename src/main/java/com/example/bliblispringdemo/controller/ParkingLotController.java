package com.example.bliblispringdemo.controller;

import com.example.bliblispringdemo.command.CommandExecutor;
import com.example.bliblispringdemo.command.GetAllParkingLotCommand;
import com.example.bliblispringdemo.command.model.GetAllParkingLotCommandRequest;
import com.example.bliblispringdemo.controller.model.request.CreateParkingLotWebRequest;
import com.example.bliblispringdemo.controller.model.response.BaseResponse;
import com.example.bliblispringdemo.controller.model.response.ParkingLotWebResponse;
import com.example.bliblispringdemo.command.CreateParkingLotCommand;
import com.example.bliblispringdemo.command.model.CreateParkingLotCommandRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking-lots")
@RequiredArgsConstructor
public class ParkingLotController {

  private final CommandExecutor commandExecutor;

  @PostMapping
  public BaseResponse<List<ParkingLotWebResponse>> createParkingLots(
    @RequestBody @Validated CreateParkingLotWebRequest createParkingLotWebRequest) {
    return BaseResponse.ok(
      commandExecutor.execute(CreateParkingLotCommand.class,
        CreateParkingLotCommandRequest.builder()
          .count(createParkingLotWebRequest.getCount())
          .build())
    );
  }

  @GetMapping
  public BaseResponse<List<ParkingLotWebResponse>> getParkingLots(
    @RequestParam(value = "skip", required = false) int skip,
    @RequestParam(value = "limit", required = false) int limit
  ) {
    return BaseResponse.ok(
      commandExecutor.execute(GetAllParkingLotCommand.class,
        GetAllParkingLotCommandRequest.builder()
          .skip(skip)
          .limit(limit)
          .build()
        )
    );
  }


  //To Do : Create an API to find specific parking lot by its ID (complete with test)
  //To Do : Create an API to assign a Vehicle to a specific parking lot (complete with test)
}
