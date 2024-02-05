package com.example.bliblispringdemo.command;

import com.example.bliblispringdemo.command.impl.CreateParkingLotCommandImpl;
import com.example.bliblispringdemo.command.model.CreateParkingLotCommandRequest;
import com.example.bliblispringdemo.repository.ParkingLotRepository;
import com.example.bliblispringdemo.repository.entity.ParkingLotEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CreateParkingLotCommandImplTest {

  private CreateParkingLotCommandImpl parkingLotCommand; //Actual class to test

  @Mock
  private ParkingLotRepository parkingLotRepository;


  @BeforeEach
  void init() {
    parkingLotCommand = new CreateParkingLotCommandImpl(parkingLotRepository);
  }

  @Test
  void execute_GivenCountMoreThanZero_ShouldPopulateRepository() {
    int count = 3;
    Mockito.when(parkingLotRepository.save(Mockito.any()))
        .thenReturn(ParkingLotEntity.builder()
          .id(UUID.randomUUID().toString())
          .vehicle(null)
          .isOccupied(false)
          .build());

    parkingLotCommand.execute(CreateParkingLotCommandRequest.builder()
      .count(count)
      .build());

    Mockito.verify(parkingLotRepository, Mockito.times(3))
      .save(Mockito.any());
  }

}
