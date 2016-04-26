package fr.iocean.application.utils;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return cal;
    }
}
