package com.example.universityactivitytracker;

import androidx.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Converters {
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @TypeConverter
    public static Date toDate(String value){
        if(value != null) {
            try {
                TimeZone timeZone = TimeZone.getTimeZone("GMT");
                df.setTimeZone(timeZone);
                return df.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        } else{
            return null;
        }
    }

    @TypeConverter
    public static String fromDate(Date value){
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        df.setTimeZone(timeZone);
        return value == null ? null : df.format(value);
    }
}
