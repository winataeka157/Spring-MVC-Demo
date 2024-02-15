package com.example.bliblispringdemo.repository;

import com.example.bliblispringdemo.exception.InvalidParameterException;
import com.example.bliblispringdemo.entity.ParkingLotEntity;

import java.util.List;

public interface ParkingLotRepository {

  ParkingLotEntity findById(String id);
  ParkingLotEntity save(ParkingLotEntity parkingLotEntity) throws InvalidParameterException;
  ParkingLotEntity delete(String id);

  List<ParkingLotEntity> findAll();
}
