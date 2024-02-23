package com.example.bliblispringdemo.controller;

import com.example.bliblispringdemo.controller.model.request.CreateParkingLotWebRequest;
import com.example.bliblispringdemo.controller.model.response.BaseResponse;
import com.example.bliblispringdemo.controller.model.response.ParkingLotWebResponse;
import com.example.bliblispringdemo.repository.ParkingLotRepository;
import com.example.bliblispringdemo.entity.ParkingLotEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class ParkingLotControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  private ParkingLotRepository parkingLotRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  @AfterEach
  public void cleanUp() {
    parkingLotRepository.deleteAll();
  }

  @Test
  void createParkingLots_GivenValidRequest_ShouldCreateParkingLots() throws Exception {
    int count = 10;
    CreateParkingLotWebRequest createParkingLotWebRequest = CreateParkingLotWebRequest.builder()
      .count(count)
      .build();

    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/parking-lots")
        .content(objectMapper.writeValueAsString(createParkingLotWebRequest))
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andReturn();
    TypeReference<BaseResponse<List<ParkingLotWebResponse>>> typeReference = new TypeReference<>() {
    };
    BaseResponse<List<ParkingLotWebResponse>> response = objectMapper.readValue(
      mvcResult.getResponse().getContentAsString(),
      typeReference
    );

    Mockito.verify(parkingLotRepository, Mockito.times(count))
      .save(Mockito.any());
    List<ParkingLotEntity> updatedEntities = parkingLotRepository.findAll();
    Assertions.assertEquals(count, response.getData().size());
    Assertions.assertEquals(updatedEntities.size(), count);
    Assertions.assertTrue(updatedEntities.stream()
      .allMatch(parkingLotEntity -> !parkingLotEntity.isOccupied() && Objects.isNull(parkingLotEntity.getVehicle())));
  }

  @Test
  void createParkingLots_GivenInvalidRequest_ShouldReturn400() throws Exception {
    int count = 1000;
    CreateParkingLotWebRequest createParkingLotWebRequest = CreateParkingLotWebRequest.builder()
      .count(count)
      .build();

    mockMvc.perform(MockMvcRequestBuilders.post("/parking-lots")
        .content(objectMapper.writeValueAsString(createParkingLotWebRequest))
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  void getParkingLots_GivenParticularDataExists_ShouldReturnListOfData() throws Exception {
    int skip = 0;
    int limit = 5;
    List<ParkingLotEntity> mockedEntities = buildMockParkingLots(limit);
    parkingLotRepository.saveAll(mockedEntities);

    MvcResult mvcResult = mockMvc.perform(
        MockMvcRequestBuilders.get("/parking-lots")
          .param("skip", String.valueOf(skip))
          .param("limit", String.valueOf(limit))
          .accept(MediaType.APPLICATION_JSON)
      ).andExpect(MockMvcResultMatchers.status().isOk())
      .andReturn();
    BaseResponse<List<ParkingLotEntity>> response = objectMapper.readValue(
      mvcResult.getResponse().getContentAsString(),
      new TypeReference<>() {}
    );

    Assertions.assertEquals(mockedEntities, response.getData());
  }

  private static List<ParkingLotEntity> buildMockParkingLots(int count) {
    return IntStream.range(0, count)
      .mapToObj(index -> ParkingLotEntity.builder()
        .id(UUID.randomUUID().toString())
        .build())
      .toList();
  }

}
