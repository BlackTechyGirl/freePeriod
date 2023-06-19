package freePeriod;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MenstralCycleDates {
    private static List<LocalDate> flowDates;
    private static LocalDate ovulationDate;
    private static List<LocalDate> fertileDates;
    private static List<LocalDate> nonFertileDates;

    public MenstralCycleDates() {
        flowDates = new ArrayList<LocalDate>();
        fertileDates = new ArrayList<LocalDate>();
        nonFertileDates = new ArrayList<LocalDate>();
    }


    public static List<LocalDate> getFlowDates(LocalDate lastPeriodDate, int cycleLength, int flowLength) {
        LocalDate nextPeriodDate = lastPeriodDate.plusDays(cycleLength);
        LocalDate flowStartDate = nextPeriodDate.minusDays(flowLength);

        flowDates = new ArrayList<LocalDate>();
        flowDates.add(nextPeriodDate);
        flowDates.add(flowStartDate);

        return flowDates;
    }

    public static LocalDate getOvulationDate(LocalDate lastPeriodDate, int cycleLength, int flowLength) {
        LocalDate nextPeriodDate = lastPeriodDate.plusDays(cycleLength);
        return nextPeriodDate.minusDays(14);
        //return nextPeriodDate.minusDays(14);
    }

    public static List<LocalDate> getFertilityDates(LocalDate lastPeriodDate, int cycleLength, int flowLength) {

        LocalDate ovulationDate = getOvulationDate(lastPeriodDate, cycleLength, flowLength);
        LocalDate fertilityStarts = ovulationDate.minusDays(5);
        LocalDate fertilityEnds = ovulationDate.plusDays(5);

        fertileDates = new ArrayList<>();
        while (fertilityStarts.isBefore(fertilityEnds.plusDays(1))) {
            fertileDates.add(fertilityStarts);
            fertilityStarts = fertilityStarts.plusDays(1);
        }
        return fertileDates;
    }


    public static List<LocalDate> getNonFertileDates(LocalDate lastPeriodDate, int cycleLength, int flowLength) {
        LocalDate ovulationDate = getOvulationDate(lastPeriodDate, cycleLength, flowLength);
//        LocalDate nonFertileStart = lastPeriodDate;

//        nonFertileDates = new ArrayList<>();
        List<LocalDate> dates = new ArrayList<>();
        for (int i = 5; i <=cycleLength-5 ; i++) {
            LocalDate nonFertile = ovulationDate.minusDays(i);
            dates.add(nonFertile);
        }
//
//        while (nonFertileStart.isBefore(nonFertileEnd.plusDays(1))) {
//            nonFertileDates.add(nonFertileStart);
//            nonFertileStart = nonFertileStart.plusDays(1);
//        }
        return dates;
    }
}
