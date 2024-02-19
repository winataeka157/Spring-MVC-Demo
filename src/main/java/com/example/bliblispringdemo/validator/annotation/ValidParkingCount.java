package com.example.bliblispringdemo.validator.annotation;

import com.example.bliblispringdemo.validator.ValidParkingCountValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidParkingCountValidator.class)
public @interface ValidParkingCount {

  String message() default "Invalid Parking Count";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
