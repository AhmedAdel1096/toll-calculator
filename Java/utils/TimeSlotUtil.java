package utils;

import model.toll.TollFees;

public enum TimeSlotUtil {
    EARLY_MORNING(6, 0, 6, 29, TollFees.FIRST_DEGREE_FEE),
    MID_MORNING(6, 30, 6, 59, TollFees.SECOND_DEGREE_FEE),
    MORNING_RUSH_HOUR(7, 0, 7, 59, TollFees.THIRD_DEGREE_FEE),
    LATE_MORNING_RUSH(8, 0, 8, 29, TollFees.SECOND_DEGREE_FEE),
    MIDDAY(8, 30, 14, 59, TollFees.FIRST_DEGREE_FEE),
    AFTERNOON(15, 0, 15, 29, TollFees.SECOND_DEGREE_FEE),
    EVENING_RUSH_HOUR(15, 30, 16, 59, TollFees.THIRD_DEGREE_FEE),
    LATE_EVENING(17, 0, 17, 59, TollFees.SECOND_DEGREE_FEE),
    NIGHT(18, 0, 18, 29, TollFees.FIRST_DEGREE_FEE);
    // Zero fees for Rest of the day
//    OFF_HOURS(18, 30, 5, 59, 0);

    private final int startHour;
    private final int startMinute;
    private final int endHour;
    private final int endMinute;
    private final int fee;

    TimeSlotUtil(int startHour, int startMinute, int endHour, int endMinute, int fee) {
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.fee = fee;
    }

    public int getFee() {
        return fee;
    }

    public boolean matches(int hour, int minute) {
        if (hour > startHour && hour < endHour) {
            return true;
        } else if (hour == startHour && hour == endHour) {
            return minute >= startMinute && minute <= endMinute;
        } else if (hour == startHour) {
            return minute >= startMinute;
        } else if (hour == endHour) {
            return minute <= endMinute;
        }
        return false;
    }


}
