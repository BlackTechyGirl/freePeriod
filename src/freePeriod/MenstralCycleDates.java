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
        LocalDate flowStartDate = nextPeriodDate.minusDays(flowLength-1);

        ArrayList<LocalDate> flowDates = new ArrayList<LocalDate>();
        flowDates.add(nextPeriodDate);
        flowDates.add(flowStartDate);

        return flowDates;
    }

    public static List<List<LocalDate>> getTwelveMonthsFlowDates(LocalDate lastPeriodDate, int cycleLength, int flowLength) {
        return getLists(lastPeriodDate, cycleLength, flowLength);
    }

    private static List<List<LocalDate>> getLists(LocalDate lastPeriodDate, int cycleLength, int flowLength) {
        List<List<LocalDate>> freePeriods = new ArrayList<>();
        LocalDate endDate = LocalDate.now().plusMonths(12);

        while (lastPeriodDate.isBefore(endDate)) {
            List<LocalDate> freeDates = getNonFertileDates(lastPeriodDate, cycleLength, flowLength);
            freePeriods.add(freeDates);

            lastPeriodDate = lastPeriodDate.plusDays(cycleLength);
        }

        return freePeriods;
    }

    public static LocalDate getOvulationDate(LocalDate lastPeriodDate, int cycleLength){
        LocalDate nextPeriodDate = lastPeriodDate.plusDays(cycleLength);
        return nextPeriodDate.minusDays(14);
    }

    public static List<List<LocalDate>> getTwelveMonthsOvulationDate(LocalDate lastPeriodDate, int cycleLength, int flowLength) {
        return getLists(lastPeriodDate, cycleLength, flowLength);
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

    public static List<List<LocalDate>> getTwelveMonthsFertileDates(LocalDate lastPeriodDate, int cycleLength, int flowLength) {
        return getLists(lastPeriodDate, cycleLength, flowLength);
    }


    public static List<LocalDate> getNonFertileDates(LocalDate lastPeriodDate, int cycleLength, int flowLength){
        LocalDate ovulationDate = getOvulationDate(lastPeriodDate, cycleLength);
        LocalDate nonFertileStart = lastPeriodDate;
        LocalDate nonFertileEnd = ovulationDate.minusDays(5);

        nonFertileDates = new ArrayList<>();
        while (nonFertileStart.isBefore(nonFertileEnd.plusDays(1))){
            nonFertileDates.add(nonFertileStart);
            nonFertileStart = nonFertileStart.plusDays(1);
        }
        return nonFertileDates;
    }

    public static List<List<LocalDate>> getTwelveMonthsNonFertileDates(LocalDate lastPeriodDate, int cycleLength, int flowLength) {
        return getLists(lastPeriodDate, cycleLength, flowLength);
    }


}
