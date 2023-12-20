package model.vehicles;

import java.util.UUID;

public class Car implements Vehicle {
  UUID carId;
  @Override
  public String getType() {
    return "Car";
  }
}
