package com.example.bliblispringdemo.controller;

import com.example.bliblispringdemo.controller.model.request.CreateParkingLotWebRequest;
import com.example.bliblispringdemo.repository.ParkingLotRepository;
import com.example.bliblispringdemo.repository.entity.ParkingLotEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Objects;

@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ParkingLotRepository parkingLotRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void createParkingLots() throws Exception {
    int count = 10;
    CreateParkingLotWebRequest createParkingLotWebRequest = CreateParkingLotWebRequest.builder()
      .count(count)
      .build();

    mockMvc.perform(MockMvcRequestBuilders.post("/parking-lots")
        .content(objectMapper.writeValueAsString(createParkingLotWebRequest))
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk());

    List<ParkingLotEntity> updatedEntities = parkingLotRepository.findAll();
    Assertions.assertEquals(updatedEntities.size(), count);
    Assertions.assertTrue(updatedEntities.stream()
      .allMatch(parkingLotEntity -> !parkingLotEntity.isOccupied() && Objects.isNull(parkingLotEntity.getVehicle())));
  }

}
