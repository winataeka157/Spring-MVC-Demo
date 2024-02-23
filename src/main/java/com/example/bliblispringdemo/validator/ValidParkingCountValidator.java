package com.example.bliblispringdemo.validator;

import com.example.bliblispringdemo.validator.annotation.ValidParkingCount;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class ValidParkingCountValidator implements ConstraintValidator<ValidParkingCount, Integer> {

  private final MessageSource messageSource;
  @Value("${parking.create-lot-count.max}")
  private int max;
  @Value("${parking.create-lot-count.min}")
  private int min;

  @Override
  public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
    if (integer == null || !(integer >= min && integer <=max)) {
      String message = messageSource.getMessage(
        "com.example.bliblispringdemo.validator.annotation.ValidParkingCount.message",
        new Object[]{min, max},
        Locale.ENGLISH
      );
      constraintValidatorContext.disableDefaultConstraintViolation();
      constraintValidatorContext.buildConstraintViolationWithTemplate(message)
        .addConstraintViolation();
      return false;
    }
    return true;
  }

}
