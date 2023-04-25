package freePeriod;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MenstralCycleDates {
    private static List<LocalDate> flowDates;
    private static LocalDate ovulationDate;
    private static List<LocalDate> fertileDates;
    private static List<LocalDate> nonFertileDates;

//    public MenstralCycleDates(List<LocalDate> flowDates, LocalDate ovulationDate,
//                              List<LocalDate> fertileDates, List<LocalDate> nonFertileDates) {
//        MenstralCycleDates.flowDates = flowDates;
//        MenstralCycleDates.ovulationDate = ovulationDate;
//        MenstralCycleDates.fertileDates = fertileDates;
//        MenstralCycleDates.nonFertileDates = nonFertileDates;

//    }

    public static List<LocalDate> getFlowDates(LocalDate lastPeriodDate, int cycleLength, int flowLength){
        LocalDate nextPeriodDate = lastPeriodDate.plusDays(cycleLength);
        LocalDate flowStartDate = nextPeriodDate.minusDays(flowLength);
        flowDates = new ArrayList<>();
        flowDates.add(nextPeriodDate);
        flowDates.add(flowStartDate);
        return flowDates;
    }
    public static LocalDate getOvulationDate(LocalDate lastPeriodDate, int cycleLength){
        LocalDate nextPeriodDate = lastPeriodDate.plusDays(cycleLength);
        return nextPeriodDate.minusDays(14);
    }
    public static List<LocalDate> getFertilityDates(LocalDate lastPeriodDate, int cycleLength){
        LocalDate ovulationDate = getOvulationDate(lastPeriodDate, cycleLength);
        LocalDate fertilityStarts = ovulationDate.minusDays(5);
        LocalDate fertilityEnds = ovulationDate.plusDays(5);

        fertileDates = new ArrayList<>();
        while(fertilityStarts.isBefore(fertilityEnds.plusDays(1))){
            fertileDates.add(fertilityStarts);
            fertilityStarts = fertilityStarts.plusDays(1);
        }
        return fertileDates;
    }


    public static List<LocalDate> getNonFertileDates(LocalDate lastPeriodDate, int cycleLength){
        LocalDate ovulationDate = getOvulationDate(lastPeriodDate,cycleLength);
        LocalDate nonFertileStart = lastPeriodDate;
        LocalDate nonFertileEnd = ovulationDate.minusDays(5);

        nonFertileDates = new ArrayList<>();
        while (nonFertileStart.isBefore(nonFertileEnd.plusDays(1))){
            nonFertileDates.add(nonFertileStart);
            nonFertileStart = nonFertileEnd.plusDays(1);
        }
        return nonFertileDates;
    }

}
