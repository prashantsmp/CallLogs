package com.call.logs.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Prashant on 9/9/2018.
 */
public class DateUtils {

    public static SimpleDateFormat getDateFormat(DateFormatType dateFormatType) {
        switch (dateFormatType) {

            case TRANSACTION_DATE_TIME:
                return new SimpleDateFormat("ddMMyyyyHHmmss", Locale.US);
            case CURRENT_TIME:
                return new SimpleDateFormat("hh:mm:a", Locale.US);
            case CALENDAR_ADAPTER_DATE:
                return new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            case APPOINTMENT_DATE:
                return new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            case EXPIRY_DATE:
                return new SimpleDateFormat("yyyyMMdd", Locale.US);
            case DUMMY_APPOINTMENT_TIME:
                return new SimpleDateFormat("yyyy-MM-dd'T'", Locale.US);
            case APPOINTMENT_TIME:
                return new SimpleDateFormat("hh:mm:ss", Locale.US);
            case APPOINTMENT_TIME_SERVER:
                return new SimpleDateFormat("HH:mm:ss", Locale.US);
            case CALENDAR_TITLE_FORMAT:
                return new SimpleDateFormat("MMMM yyyy", Locale.US);
            case INCOMING_CALL_TIME:
                return new SimpleDateFormat("dd/MM/yyyy hh:mm:a", Locale.US);
            case NORMAL_DATE_TIME:
                return new SimpleDateFormat("dd/MM/yyyy hh:mm:a", Locale.US);
            case PRESCRIPTION_ALARM_FORMAT:
                return new SimpleDateFormat("dd MMM, hh:mm a", Locale.US);
            case FILE_NAME_CREATION:
                return new SimpleDateFormat("ddMMyyyyhhmmss", Locale.US);
            case EXERCISE_DATE:
                return new SimpleDateFormat("E, dd MMM yyyy hh:mm a", Locale.US);
            case VACCINATION_DATE:
                return new SimpleDateFormat("E, dd MMM yyyy", Locale.US);
            case APPOINTMENT_HISTORY_FORMAT:
                return new SimpleDateFormat("yyyyMMdd", Locale.US);
            case DATE_MONTH_FORMAT:
                return new SimpleDateFormat("ddMMyyyy", Locale.US);
            case DEFAULT:
                return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
            default:
                return new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        }
    }


    public static String getIncomingCallTime() {
        Calendar cal = Calendar.getInstance();
        return getDateFormat(DateFormatType.INCOMING_CALL_TIME).format(cal.getTime());
    }

    public static String getCurrentDateTime() {
        Calendar cal = Calendar.getInstance();
        return getDateFormat(DateFormatType.NORMAL_DATE_TIME).format(cal.getTime());
    }

    private static int calculateAge(Date birthDate) {
        long timeBetween = new Date().getTime() - birthDate.getTime();
        double yearsBetween = timeBetween / 3.15576e+10;
        return (int) Math.floor(yearsBetween);
    }

    private static int calculateMonth(Date birthDate) {
        Calendar todayCal = Calendar.getInstance();
        todayCal.setTime(new Date());
        Calendar birthCal = Calendar.getInstance();
        birthCal.setTime(birthDate);
        return todayCal.get(Calendar.MONTH)
                - birthCal.get(Calendar.MONTH);
    }

    public static boolean isFutureStartDate(Date prescription) {
        Calendar now = Calendar.getInstance();
        Calendar prescriptionDate = Calendar.getInstance();
        prescriptionDate.setTime(prescription);
        return prescriptionDate.after(now);
    }

    public static boolean isFutureEndDate(Date prescription) {
        Calendar now = Calendar.getInstance();
        Calendar prescriptionDate = Calendar.getInstance();
        prescriptionDate.setTime(prescription);
        return prescriptionDate.after(now);
    }

}
