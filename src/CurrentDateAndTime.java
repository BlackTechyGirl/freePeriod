import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class CurrentDateAndTime {
    public static void main(String[] args) {
//        4 different ways to get current date and time
//        Before java 8
//        1. SimpleDateFormat

//        String date = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")
//                .format(Calendar.getInstance().getTime());
//        System.out.println(date);

//        2. Date

//        Date date1 = new Date(System.currentTimeMillis());
//        System.out.println(date1);

//        After java 8
//        3. LocalDate

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

//        4. LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
    }
}
