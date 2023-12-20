import model.vehicles.Car;
import tollcalculator.TollCalculator;

import java.util.Calendar;
import java.util.Date;

import static utils.DateUtil.createDate;

public class TollCalculatorApp {
    public static void main(String[] args) {
        final Car car = new Car();
        final TollCalculator calculator = new TollCalculator();
        final Date date1 = createDate(2014, Calendar.AUGUST, 4, 6, 12);
        final Date date2 = createDate(2014, Calendar.AUGUST, 4, 8, 12);
        System.out.println(calculator.getTollFee(car, date1, date2));

    }
}
