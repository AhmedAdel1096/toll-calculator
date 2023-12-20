package model.date;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum Weekend {
    SATURDAY(7),
    SUNDAY(1);

    private final int day;
    Weekend(int day){
        this.day = day;
    }

    private int getDay() {
        return this.day;
    }

    public static Set<Integer> getWeekends() {
        return Arrays.stream(Weekend.values())
                .map(Weekend::getDay)
                .collect(Collectors.toSet());
    }
}
