package com.example.bliblispringdemo.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseErrorResponse {

  private int code;
  private String status;
  private Map<String, List<String>> errors;

}
