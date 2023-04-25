package freePeriod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CalendarApp {

    public static void main(String[] args) {
        LocalDate lastPeriodDate = LocalDate.of(2023, 4, 1); // Replace with actual last period date
        int cycleLength = 28; // Replace with actual cycle length
        int flowDuration = 5; // Replace with actual duration of menstrual flow

        LocalDate nextPeriodDate = lastPeriodDate.plusDays(cycleLength);
        LocalDate flowStartDate = nextPeriodDate.minusDays(flowDuration);

        System.out.println("Next period date: " + nextPeriodDate);
        System.out.println("Menstrual flow start date: " + flowStartDate);
    }

}
