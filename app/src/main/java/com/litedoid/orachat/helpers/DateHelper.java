package com.litedoid.orachat.helpers;


import android.util.Log;

import com.litedoid.orachat.Constants;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateHelper
{
    private static final String TAG = DateHelper.class.getSimpleName();

    //SERVER DATE ISO8601
    //2016-12-25T12:00:00+0000

    public static DateTime serverValueToDate(String serverTime)
    {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(Constants.DATE_SERVER_FORMAT);
        DateTime dateTime = formatter.withZoneUTC().parseDateTime(serverTime);

        Log.d(TAG, "serverValueToDate: " + dateTime.toString());

        return dateTime;
    }

    static public Boolean isSameDate(DateTime dt1, DateTime dt2)
    {
        LocalDate ld1 = new LocalDate(dt1);
        LocalDate ld2 = new LocalDate(dt2);

        return ld1.equals(ld2);
    }


    public static String getShortDate(DateTime date)
    {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(Constants.DATE_SHORT_FORMAT);
        return dateTimeFormatter.print(date);
    }

}
