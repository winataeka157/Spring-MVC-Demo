package com.example.bliblispringdemo.repository;

import com.example.bliblispringdemo.entity.ParkingLotEntity;

import java.util.List;

public interface ParkingLotRepository {

  ParkingLotEntity save(ParkingLotEntity parkingLotEntity);
  List<ParkingLotEntity> saveAll(List<ParkingLotEntity> parkingLotEntities);
  List<ParkingLotEntity> findAll();
  void deleteAll();

}
