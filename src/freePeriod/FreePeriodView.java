package freePeriod;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;

public class FreePeriodView {
    public static void main(String[] args) {

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
            List<LocalDate> fertileDates = MenstralCycleDates.getFertilityDates(lastPeriodDate, cycleLength, flowLength);
            List<LocalDate> nonFertileDates = MenstralCycleDates.getNonFertileDates(lastPeriodDate, cycleLength, flowLength);

            StringBuilder message = new StringBuilder();
            message.append("Month ").append(i).append(":\n");
            message.append("Next period date: ").append(flowDates.get(0).format(formatter)).append("\n");
            message.append("Flow start date: ").append(flowDates.get(1).format(formatter)).append("\n");
            message.append("Ovulation date: ").append(ovulationDate.format(formatter)).append("\n");
            message.append("Fertile dates: ");
            for (LocalDate date : fertileDates) {
                message.append(date.format(formatter)).append(", ");
            }
            message.delete(message.length() - 2, message.length());
            message.append("\nNon-fertile dates: ");
            for (LocalDate date : nonFertileDates) {
                message.append(date.format(formatter)).append(", ");
            }
            message.delete(message.length() - 2, message.length());

            JOptionPane.showMessageDialog(null, message.toString(), "Menstrual Cycle Dates", JOptionPane.INFORMATION_MESSAGE);

            lastPeriodDate = flowDates.get(0);
        }
    }
}
