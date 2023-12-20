package test;

import model.vehicles.Car;
import model.vehicles.Motorbike;
import model.vehicles.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tollcalculator.TollCalculator;

import java.util.Date;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static utils.DateUtil.createDate;

class TollCalculatorTest {
    private TollCalculator tollCalculator;

    @BeforeEach
    void setUp() {
        tollCalculator = new TollCalculator();
    }

    @ParameterizedTest
    @MethodSource("getDifferentDates")
    void givenMultipleSlots_whenCalculateTollFees_thenFeesCalculatedSuccessfully(Date inputDate) {
        // GIVEN
        Vehicle car = new Car();
        //WHEN
        int fees = tollCalculator.getTollFee(car, inputDate);
        // THEN
        if(inputDate.getHours() == 6 && inputDate.getMinutes() == 15)  assertEquals(8, fees);
        else if(inputDate.getHours() == 6 && inputDate.getMinutes() == 45)  assertEquals(13, fees);
        else if(inputDate.getHours() == 11 && inputDate.getMinutes() == 45)  assertEquals(8, fees);
        else if (inputDate.getHours() == 15 && inputDate.getMinutes() == 30)  assertEquals(18, fees);
        else if (inputDate.getHours() == 19 && inputDate.getMinutes() == 30)  assertEquals(0, fees);
    }
    private static Stream<Arguments> getDifferentDates(){
        return Stream.of(
                Arguments.of(createDate(2023, 1, 2, 6, 15)),
                Arguments.of(createDate(2023, 1, 2, 6, 45)),
                Arguments.of(createDate(2023, 1, 2, 11, 45)),
                Arguments.of(createDate(2023, 1, 2, 15, 30)),
                Arguments.of(createDate(2023, 1, 2, 19, 30)));
    }
    @Test
    void givenFreeFeesVehicle_whenCalculateFees_thenZeroFeesReturned() {
        // GIVEN
        Vehicle motorbike = new Motorbike();
        Date date = createDate(2023, 1, 2, 7, 30); // A non-toll-free date

        //WHEN
        int feeFreeVehicle = tollCalculator.getTollFee(motorbike, date);

        // THEN
        assertEquals(0, feeFreeVehicle,"feeFreeVehicle assertion failed");

    }
    @Test
    void givenFreeFeesDate_whenCalculateFees_thenZeroFeesReturned() {

        //GIVEN
        Vehicle car = new Car();
        Date freeDate = createDate(2013,1,1,7,30);
        Date weekend = createDate(2023,1,1,7,30);


        //WHEN
        int feeWeekend = tollCalculator.getTollFee(car,weekend);
        int feeFreeDate = tollCalculator.getTollFee(car,freeDate);

        //THEN
        assertEquals(0, feeFreeDate,"feeFreeDate assertion failed");
        assertEquals(0, feeWeekend,"feeWeekend assertion failed");
    }

    @Test
    void givenCarExceedMaximumDailyFeeLimit_whenCalculateFees_thenMaximumDailyFeesReturned() {
        // GIVEN
        Date earlyMorningPass = createDate(2023, 1, 2, 6, 15);
        Date morningRushHourPass = createDate(2023, 1, 2, 7, 30);
        Date midDayPass = createDate(2023, 1, 2, 9, 30);
        Date eveningRushHourPass = createDate(2023,1,2,15,45);
        Date nightPass = createDate(2023,1,2,18,10);
        Vehicle car = new Car();

        // WHEN
        int totalFee = tollCalculator.getTollFee(car, earlyMorningPass,morningRushHourPass, midDayPass,eveningRushHourPass,nightPass);

        // THEN
        assertEquals(60, totalFee);
    }

    @Test
    void givenMultipleCarPassesWithinSameHour_whenCalculateFees_thenTheHigherFeeIsReturned() {
        //GIVEN
        Date firstPassMorningRush = createDate(2023, 1, 2, 8, 15);
        Date secondPassLateMorningRush = createDate(2023, 1, 2, 8, 30);
        Vehicle car = new Car();

        // WHEN
        int totalFee = tollCalculator.getTollFee(car, firstPassMorningRush, secondPassLateMorningRush);

        //THEN
        //(Second degree fee = 13 for Morning rush)
        assertEquals(13, totalFee);
    }


}