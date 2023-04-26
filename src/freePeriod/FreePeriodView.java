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
            LocalDate lastPeriodDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            String cycleLengthString = JOptionPane.showInputDialog(
                    null, "Please, enter your cycle length in days: ");
            int cycleLength = Integer.parseInt(cycleLengthString);

            String flowLengthString = JOptionPane.showInputDialog(null,
                    "Please, enter your flow length in days: ");
            int flowLength = Integer.parseInt(flowLengthString);

            List<LocalDate> flowDates = MenstralCycleDates.getFlowDates(lastPeriodDate, cycleLength, flowLength);
            LocalDate ovulationDate = MenstralCycleDates.getOvulationDate(lastPeriodDate, cycleLength);
            List<LocalDate> fertileDates = MenstralCycleDates.getFertilityDates(lastPeriodDate, cycleLength);
            List<LocalDate> nonFertileDates = MenstralCycleDates.getNonFertileDates(lastPeriodDate, cycleLength, flowLength);
            List<List<LocalDate>> next12MonthDates = MenstralCycleDates.getFreePeriodDates(lastPeriodDate, cycleLength, flowLength);

            StringBuilder strings = new StringBuilder();
            strings.append("Your flow starts on: ").append(flowDates.get(1)).append("\n");
            strings.append("Your flow ends on: ").append(flowDates.get(0)).append("\n");
            strings.append("Your ovulation date is: ").append(ovulationDate).append("\n");
            strings.append("Your fertile days are: ").append(fertileDates).append("\n");
            strings.append("Your non-fertile days are: ").append(nonFertileDates).append("\n");

            JOptionPane.showMessageDialog(null, strings.toString(), "Your menstral cycle: ", JOptionPane.INFORMATION_MESSAGE);
            for (List<LocalDate> nextDate : next12MonthDates) {
                String message = "Free period dates:\n";
                for (LocalDate date : nextDate) {
                    message += date.format(DateTimeFormatter.ISO_LOCAL_DATE) + "\n";
                } JOptionPane.showMessageDialog(null, message);
            }
            strings.append("Next month is: ").append(next12MonthDates).append("\n");
        }catch (DateTimeParseException e){
            JOptionPane.showMessageDialog(null, "This date is invalid. " +
                    "Please enter a date in this format(dd/mm/yyyy). " );
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid input. " +
                    "Please enter a valid number");
        }
    }
}
