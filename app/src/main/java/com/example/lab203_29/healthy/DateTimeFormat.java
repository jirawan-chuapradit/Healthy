package com.example.lab203_29.healthy;

import android.util.Log;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormat {

    private static DateTimeFormat dateTimeFormatInstance;
    private int hour;
    private int min;
    private String format = "hh:mm";

    SimpleDateFormat sdf = new SimpleDateFormat(format);
//    DecimalFormat decimalFormat = new DecimalFormat("###,###");

    public static DateTimeFormat getDateTimeFormatInstance(){
        if(dateTimeFormatInstance == null){
            dateTimeFormatInstance = new DateTimeFormat();
        }
        return dateTimeFormatInstance;
    }
    //Default constructor
    private DateTimeFormat() {
    }


    public void converseDateTime( String wakeUpTime, String sleepTime){
        try {

            Date dateObj1 = sdf.parse(wakeUpTime);
            Log.d("wake up Time format :" , dateObj1.toString());


            Date dateObj2 = sdf.parse(sleepTime);
            Log.d("sleep Time format : " , dateObj2.toString());

            /*
            1 minute = 60 seconds
	        1 hour = 60 x 60 = 3600
	        1 day = 3600 x 24 = 86400
             */
            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;


            // getTime() returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object
            long diff =  Math.abs(dateObj2.getTime() - dateObj1.getTime());

            long elapsedDays = diff / daysInMilli;
            diff = diff % daysInMilli;

            long elapsedHours = diff / hoursInMilli;
            diff = diff % hoursInMilli;
            System.out.println("difference between hours: " + elapsedHours);

            if(wakeUpTime.equals("12:00")||sleepTime.equals("12:00")){
                elapsedHours = Math.abs(elapsedHours - 12);
            }

            long elapsedMinutes = diff / minutesInMilli;
            diff = diff % minutesInMilli;

            System.out.println("difference between minutues: " + elapsedMinutes);


            this.hour = (int) elapsedHours;
            this.min = (int) elapsedMinutes;

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    //getter, setter

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
