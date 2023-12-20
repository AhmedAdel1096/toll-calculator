package utils;

import model.date.FreeDate;
import model.date.Weekend;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

public class DateUtil {
    private static final Set<String> FREE_DATES = FreeDate.getFreeDates();
    public static Boolean isTollFreeDate(Date date) {
        //Why final???
        final Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        final int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        if (Weekend.getWeekends().contains(dayOfWeek)) return true;

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final String isFreeDate = month + "," + day;
        //If more years to be added in the future, make the years dynamic
        //And create a free years enum in model (If the free dates are the same)
        //Otherwise, we will need an enum for each year
        if (year == 2013) return FREE_DATES.contains(isFreeDate);
        return false;
    }

    public static Date createDate(int year, int month, int day, int hour, int minute) {
        Calendar calendar = new GregorianCalendar(year, month - 1, day, hour, minute);
        return calendar.getTime();
    }
}
