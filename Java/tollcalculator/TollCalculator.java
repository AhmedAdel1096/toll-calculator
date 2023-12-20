package tollcalculator;

import model.toll.TollFees;
import model.vehicles.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import utils.TimeSlotUtil;

import java.util.*;
import java.util.concurrent.*;

import static utils.DateUtil.isTollFreeDate;
import static utils.VehicleUtil.isTollFreeVehicle;

public class TollCalculator {

  /**
   * Calculate the total toll fee for one day
   *
   * @param vehicle - the vehicle
   * @param dates   - date and time of all passes on one day
   * @return - the total toll fee for that day
   */
  private static final Logger logger = LogManager.getLogger(TollCalculator.class);
  public int getTollFee(Vehicle vehicle, Date... dates) {
    try {

    if (dates == null || dates.length == 0) throw new IllegalArgumentException("At least one date must be provided.");
    if (vehicle == null) throw new IllegalArgumentException("No vehicle provided.");
    if(isTollFreeVehicle(vehicle.getType())) return 0;

    //This solution assumes that the input dates array is sorted in ascending order
    Date currentDate = dates[0];
    int currentDateFee = getTollFeeValue(currentDate);
    int totalFee = 0;

    for (int i = 1; i < dates.length; i++) {
      Date nextDate = dates[i];
      int nextFee = getTollFeeValue(nextDate);

      if (isWithinHour(nextDate, currentDate)) {
        currentDateFee = Math.max(currentDateFee, nextFee);
      } else {
        totalFee += currentDateFee;
        currentDate = nextDate;
        currentDateFee = nextFee;
      }
    }
    totalFee += currentDateFee;
    int finalFee = Math.min(totalFee, TollFees.MAX_DAILY_FEE);
    //Logging
    ThreadContext.put("vehicleId", UUID.randomUUID().toString()); //uuid id field to be added later inside vehicle model
    ThreadContext.put("vehicleType", vehicle.getType());
    ThreadContext.put("totalFee", Integer.toString(finalFee));
    ThreadContext.put("message", "Success");
    logger.info("Toll fee calculated successfully.");
    return finalFee;
    } catch (Exception e) {
      ThreadContext.put("vehicleId", UUID.randomUUID().toString());
      ThreadContext.put("message", e.getMessage());
      logger.info("Fee Calculation failed");
    }finally {
      ThreadContext.clearAll();
    }

    return 0;
  }

  //Checks the free dates (per year and weekends) and returns the fee values
  public int getTollFeeValue(final Date date) {
    if(isTollFreeDate(date)) return 0;
    final Calendar calendar = GregorianCalendar.getInstance();
    calendar.setTime(date);
    final int hour = calendar.get(Calendar.HOUR_OF_DAY);
    final int minute = calendar.get(Calendar.MINUTE);
    for (TimeSlotUtil slot : TimeSlotUtil.values())
    {
      if(slot.matches(hour,minute)) {
        return slot.getFee();
      }
    }
    return 0;
  }

  private boolean isWithinHour(Date date1, Date date2) {
    long diff = date1.getTime() - date2.getTime();
    return TimeUnit.MILLISECONDS.toMinutes(diff) <= 60;
  }
}




