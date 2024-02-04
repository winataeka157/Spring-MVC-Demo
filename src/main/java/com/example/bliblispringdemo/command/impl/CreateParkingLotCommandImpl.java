package com.example.bliblispringdemo.command.impl;

import com.example.bliblispringdemo.command.model.CreateParkingLotCommandRequest;
import com.example.bliblispringdemo.controller.model.response.ParkingLotWebResponse;
import com.example.bliblispringdemo.repository.ParkingLotRepository;
import com.example.bliblispringdemo.repository.entity.ParkingLotEntity;
import com.example.bliblispringdemo.command.CreateParkingLotCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class CreateParkingLotCommandImpl implements CreateParkingLotCommand {

  private final ParkingLotRepository parkingLotRepository;

  @Override
  public List<ParkingLotWebResponse> execute(CreateParkingLotCommandRequest request) {
    return IntStream.range(0, request.getCount())
      .mapToObj(index -> parkingLotRepository.save(ParkingLotEntity.builder()
        .isOccupied(false)
        .build()))
      .map(parkingLotEntity -> ParkingLotWebResponse.builder()
        .id(parkingLotEntity.getId())
        .isOccupied(parkingLotEntity.isOccupied())
        .vehicle(null)
        .build())
      .collect(Collectors.toList());
  }

}
