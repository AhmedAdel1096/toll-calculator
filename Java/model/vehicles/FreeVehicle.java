package model.vehicles;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum FreeVehicle {
    MOTORBIKE("Motorbike"),
    TRACTOR("Tractor"),
    EMERGENCY("Emergency"),
    DIPLOMAT("Diplomat"),
    FOREIGN("Foreign"),
    MILITARY("Military");
    private final String type;
    FreeVehicle(String value) {
        this.type = value;
    }
    public String getType(){return type;}

    public static Set<String> getFreeVehicles() {

        return Arrays.stream(FreeVehicle.values())
                .map(FreeVehicle::getType)
                .collect(Collectors.toSet());
    }
}
