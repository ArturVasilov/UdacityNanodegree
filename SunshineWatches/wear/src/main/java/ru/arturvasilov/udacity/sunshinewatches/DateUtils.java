package ru.arturvasilov.udacity.sunshinewatches;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Calendar;

/**
 * @author Artur Vasilov
 */
public final class DateUtils {

    private DateUtils() {
    }

    @NonNull
    public static String getDayOfWeek(@NonNull Context context, int day) {
        if (day >= Calendar.SUNDAY && day <= Calendar.SATURDAY) {
            return context.getResources().getStringArray(R.array.days)[day - 1];
        }
        return "";
    }

    @NonNull
    public static String getMonthOfYear(@NonNull Context context, int month) {
        if (month >= Calendar.JANUARY && month <= Calendar.DECEMBER) {
            return context.getResources().getStringArray(R.array.months)[month];
        }
        return "";
    }

}
