package com.example.bliblispringdemo.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "parking")
public class ParkingProperties {

  private int lotInitialCapacity;

}
