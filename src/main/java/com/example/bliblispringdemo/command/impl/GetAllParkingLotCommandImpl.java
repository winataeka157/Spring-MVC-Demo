package com.example.bliblispringdemo.command.impl;

import com.example.bliblispringdemo.command.GetAllParkingLotCommand;
import com.example.bliblispringdemo.command.model.GetAllParkingLotCommandRequest;
import com.example.bliblispringdemo.controller.model.response.ParkingLotWebResponse;
import com.example.bliblispringdemo.entity.ParkingLotEntity;
import com.example.bliblispringdemo.repository.ParkingLotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAllParkingLotCommandImpl implements GetAllParkingLotCommand {

  private final ParkingLotRepository parkingLotRepository;

  @Override
  public List<ParkingLotWebResponse> execute(GetAllParkingLotCommandRequest request) {
    return parkingLotRepository.findAll()
      .stream()
      .skip(request.getSkip())
      .limit(request.getLimit())
      .map(GetAllParkingLotCommandImpl::toWebParkingLotResponse
      )
      .collect(Collectors.toList());
  }

  private static ParkingLotWebResponse toWebParkingLotResponse(ParkingLotEntity parkingLotEntity) {
    return ParkingLotWebResponse.builder()
      .id(parkingLotEntity.getId())
      .isOccupied(parkingLotEntity.isOccupied())
      .vehicle(toVehicleWebResponse(parkingLotEntity)
      ).build();
  }

  private static ParkingLotWebResponse.Vehicle toVehicleWebResponse(ParkingLotEntity parkingLotEntity) {
    return Optional.ofNullable(parkingLotEntity.getVehicle())
      .map(vehicleEntity -> ParkingLotWebResponse.Vehicle
        .builder()
        .color(vehicleEntity.getColor())
        .registrationNumber(vehicleEntity.getRegistrationNumber())
        .build()
      )
      .orElse(null);
  }

}
