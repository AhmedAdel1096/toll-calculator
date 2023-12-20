package model.date;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum FreeDate {

    JanHoliday(0, 1),
    MarchFirstHoliday(2, 28),
    MarchSecondHoliday(2, 29),
    AprilFirstHoliday(3, 1),
    AprilSecondHoliday(3, 30),
    MayFirstHoliday(4, 1),
    MaySecondHoliday(4, 8),
    MayThirdHoliday(4, 9),
    JuneFirstHoliday(5, 5),
    JuneSecondHoliday(5, 6),
    JuneThirdHoliday(5, 21),
    JulyHoliday(6, 1),
    NovemberHoliday(10, 1),
    DecFirstHoliday(11, 24),
    DecSecondHoliday(11, 25),
    DecThirdHoliday(11, 26),
    DecForthHoliday(11, 31);

    private final int month;
    private final int day;

    FreeDate(int month, int day) {
        this.month = month;
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public static Set<String> getFreeDates() {
        //Month and day are comma separated
        return Arrays.stream(FreeDate.values())
                .map(freeDate -> new String(freeDate.getMonth() + "," + freeDate.getDay()))
                .collect(Collectors.toSet());
    }
}
