package com.example.bliblispringdemo.controller;

import com.example.bliblispringdemo.controller.model.response.BaseErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorController {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public BaseErrorResponse handleControllerValidation(MethodArgumentNotValidException exception) {
    return BaseErrorResponse.builder()
      .status(HttpStatus.BAD_REQUEST.name())
      .code(HttpStatus.BAD_REQUEST.value())
      .errors(toErrorMap(exception))
      .build();
  }

  private Map<String, List<String>> toErrorMap(MethodArgumentNotValidException exception) {
    return exception.getBindingResult()
      .getFieldErrors()
      .stream()
      .map(e -> Map.entry(e.getField(), List.of(e.getDefaultMessage())))
      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }
}
