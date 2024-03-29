package com.example.bliblispringdemo.repository.impl;

import com.example.bliblispringdemo.property.ParkingProperties;
import com.example.bliblispringdemo.repository.ParkingLotRepository;
import com.example.bliblispringdemo.entity.ParkingLotEntity;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.IntStream;

@Repository
public class ParkingLotRepositoryImpl implements ParkingLotRepository {

  private Map<String, ParkingLotEntity> idToParkingLotMap;
  private final ParkingProperties parkingProperties;

  @Autowired
  public ParkingLotRepositoryImpl(ParkingProperties parkingProperties) {
    this.parkingProperties = parkingProperties;
  }

  @PostConstruct
  public void initialize() {
    idToParkingLotMap = new LinkedHashMap<>();
    IntStream.range(0, parkingProperties.getLotInitialCapacity())
      .forEach(index -> {
        String id = UUID.randomUUID().toString();
        idToParkingLotMap.put(id, ParkingLotEntity.builder()
          .isOccupied(false)
          .id(id)
          .build());
      });
  }

  @Override
  public ParkingLotEntity save(@NotNull ParkingLotEntity parkingLotEntity) {
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
  public List<ParkingLotEntity> saveAll(List<ParkingLotEntity> parkingLotEntities) {
    parkingLotEntities
      .forEach(parkingLotEntity -> {
        if (Objects.isNull(parkingLotEntity.getId())) {
          parkingLotEntity.setId(UUID.randomUUID().toString());
        }
        idToParkingLotMap.put(parkingLotEntity.getId(), parkingLotEntity);
      });
    return parkingLotEntities;
  }

  @Override
  public List<ParkingLotEntity> findAll() {
    return idToParkingLotMap.values()
      .stream()
      .toList();
  }

  @Override
  public void deleteAll() {
     idToParkingLotMap = new LinkedHashMap<>();
  }

}
