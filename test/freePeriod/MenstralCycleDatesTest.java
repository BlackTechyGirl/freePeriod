package freePeriod;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MenstralCycleDatesTest {
    private LocalDate lastPeriodDate;
    private int cycleLength;
    private int flowLength;
    @BeforeEach
    void setUp() {
        lastPeriodDate = LocalDate.of(2023, 3, 31);
        cycleLength = 28;
        flowLength = 5;

    }

    @Test
    void getFlowDates() {
        List<LocalDate> expectedFlowDates = List.of(LocalDate.of(2023, 4, 28), LocalDate.of(2023, 4, 23));
        List<LocalDate> actualFlowDates = MenstralCycleDates.getFlowDates(lastPeriodDate, cycleLength, flowLength);
        assertEquals(expectedFlowDates, actualFlowDates);
    }

    @Test
    void getOvulationDate() {
        LocalDate expectedOvulationDate = LocalDate.of(2023, 4, 14);
        LocalDate actualOvulationDate = MenstralCycleDates.getOvulationDate(lastPeriodDate, cycleLength);
        assertEquals(expectedOvulationDate, actualOvulationDate);

    }

    @Test
    void getFertilityDates() {
        List<LocalDate> expectedFertilityDates = List.of(
                LocalDate.of(2023, 4, 9),
                LocalDate.of(2023, 4, 10),
                LocalDate.of(2023, 4, 11),
                LocalDate.of(2023, 4, 12),
                LocalDate.of(2023, 4, 13),
                LocalDate.of(2023, 4, 14),
                LocalDate.of(2023, 4, 15),
                LocalDate.of(2023, 4, 16),
                LocalDate.of(2023, 4, 17),
                LocalDate.of(2023, 4, 18),
                LocalDate.of(2023, 4, 19)
        );

        List<LocalDate> actualFertilityDates = MenstralCycleDates.getFertilityDates(lastPeriodDate, cycleLength);
        assertEquals(expectedFertilityDates, actualFertilityDates);

    }

    @Test
    void getNonFertileDates() {
        List<LocalDate> expectedDates = List.of(
                LocalDate.of(2022, 4, 1),
                LocalDate.of(2022, 4, 2),
                LocalDate.of(2022, 4, 3),
                LocalDate.of(2022, 4, 4),
                LocalDate.of(2022, 4, 5),
                LocalDate.of(2022, 4, 6),
                LocalDate.of(2022, 4, 7),
                LocalDate.of(2022, 4, 8),
                LocalDate.of(2022, 4, 9),
                LocalDate.of(2022, 4, 10),
                LocalDate.of(2022, 4, 11),
                LocalDate.of(2022, 4, 12)
        );
        List<LocalDate> actualNonFertileDates = MenstralCycleDates.getNonFertileDates(lastPeriodDate, cycleLength);
        assertEquals(expectedDates, actualNonFertileDates);
    }
}