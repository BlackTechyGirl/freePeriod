package freePeriod;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;

public class FreePeriodView {
    public static void main(String[] args) {
        try {
            String dateString = JOptionPane.showInputDialog(
                    null, "Please, enter your last period date(dd/mm/yyyy): ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate lastPeriodDate = LocalDate.parse(dateString, formatter);

            String cycleLengthString = JOptionPane.showInputDialog(
                    null, "Please, enter your cycle length in days: ");
            int cycleLength = Integer.parseInt(cycleLengthString);

            String flowLengthString = JOptionPane.showInputDialog(null,
                    "Please, enter your flow length in days: ");
            int flowLength = Integer.parseInt(flowLengthString);

            for (int i = 1; i <= 12; i++) {
                List<LocalDate> flowDates = MenstralCycleDates.getFlowDates(lastPeriodDate, cycleLength, flowLength);
                LocalDate ovulationDate = MenstralCycleDates.getOvulationDate(lastPeriodDate, cycleLength, flowLength);
//                LocalDate ovulationDate1 = lastPeriodDate.plusDays(14);
                List<LocalDate> fertileDates = MenstralCycleDates.getFertilityDates(lastPeriodDate, cycleLength, flowLength);
                List<LocalDate> nonFertileDates = MenstralCycleDates.getNonFertileDates(lastPeriodDate, cycleLength, flowLength);
                LocalDate flowEnds = flowDates.get(0).plusDays(flowLength-1);

                StringBuilder message = new StringBuilder();
                message.append("Month ").append(i).append(":\n");
                message.append("Flow start date: ").append(flowDates.get(0).format(formatter)).append("\n");
                message.append("Flow end date: ").append(flowEnds.format(formatter)).append("\n");
                message.append("Ovulation date: ").append(ovulationDate.format(formatter)).append("\n\n");
                message.append("Fertile dates: \n");
                for (LocalDate date : fertileDates) {
                    message.append(date.format(formatter)).append("\n");
                }
                message.append("\nNon-fertile dates: \n");
                for (LocalDate date : nonFertileDates) {
                    message.append(date.format(formatter)).append(", ");
                }
                JOptionPane.showMessageDialog(null, message.toString(), "Menstrual Cycle Dates", JOptionPane.INFORMATION_MESSAGE);
                lastPeriodDate = flowDates.get(0);
            }
        }catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Invalid date format. Please enter a date in the format dd/mm/yyyy.");

        }
    }
}
