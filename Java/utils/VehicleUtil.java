package utils;

import java.util.Set;

import static model.vehicles.FreeVehicle.getFreeVehicles;

public class VehicleUtil {

    private static final Set<String> VEHICLE_TYPES = getFreeVehicles();

    public static boolean isTollFreeVehicle(String vehicleType) {
        if(vehicleType == null || vehicleType.isEmpty())
            throw new IllegalArgumentException("Vehicle type cannot be null or empty");

        return VEHICLE_TYPES.contains(vehicleType);
    }
}

