package com.example.bliblispringdemo.repository;

import com.example.bliblispringdemo.exception.InvalidParameterException;
import com.example.bliblispringdemo.repository.entity.ParkingLotEntity;

public interface ParkingLotRepository {

  ParkingLotEntity findById(String id);
  ParkingLotEntity save(ParkingLotEntity parkingLotEntity) throws InvalidParameterException;
  ParkingLotEntity delete(String id);

}
