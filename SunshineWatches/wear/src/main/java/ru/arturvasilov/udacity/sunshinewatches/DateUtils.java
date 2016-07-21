package ru.arturvasilov.udacity.sunshinewatches;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author Artur Vasilov
 */
public final class DateUtils {

    public static final String HH_MM = "HH:mm";
    public static final String DD_MM_YY = "dd.MM.yy";
    public static final String HH_MM_DD_MM_YY = "HH:mm dd.MM.yy";

    private static final long DAY_IN_MS = android.text.format.DateUtils.DAY_IN_MILLIS;

    private DateUtils() {
    }

    @NonNull
    public static String format(@NonNull String format, long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }

}
