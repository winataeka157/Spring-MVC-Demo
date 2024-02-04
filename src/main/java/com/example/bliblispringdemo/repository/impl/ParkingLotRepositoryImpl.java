package com.example.bliblispringdemo.repository.impl;

import com.example.bliblispringdemo.exception.InvalidParameterException;
import com.example.bliblispringdemo.repository.ParkingLotRepository;
import com.example.bliblispringdemo.repository.entity.ParkingLotEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.UUID;

@Repository
public class ParkingLotRepositoryImpl implements ParkingLotRepository {

  private final Map<String, ParkingLotEntity> idToParkingLotMap;

  public ParkingLotRepositoryImpl() {
    this.idToParkingLotMap = new TreeMap<>();
  }

  @Override
  public ParkingLotEntity findById(String id) {
    return idToParkingLotMap.get(id);
  }

  @Override
  public ParkingLotEntity save(ParkingLotEntity parkingLotEntity) {
    if (Objects.isNull(parkingLotEntity)) {
      throw new InvalidParameterException("Entity couldn't be null");
    }
    if (Objects.isNull(parkingLotEntity.getId())) {
      String id = UUID.randomUUID().toString();
      parkingLotEntity.setId(id);
      idToParkingLotMap.put(id, parkingLotEntity);
      return parkingLotEntity;
    }
    idToParkingLotMap.put(parkingLotEntity.getId(), parkingLotEntity);
    return parkingLotEntity;
  }

  @Override
  public ParkingLotEntity delete(String id) {
    if (!idToParkingLotMap.containsKey(id)) {
      throw new InvalidParameterException("Invalid ID");
    }
    return idToParkingLotMap.remove(id);
  }
}
