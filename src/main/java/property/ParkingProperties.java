package property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "parking")
public class ParkingProperties {

  private String lotCapacity;
  private String lotName;

}
