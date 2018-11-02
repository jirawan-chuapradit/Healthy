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
    private String format =  "hh:mm";

    SimpleDateFormat sdf = new SimpleDateFormat(format);
    DecimalFormat decimalFormat = new DecimalFormat("###,###");

    public static DateTimeFormat getDateTimeFormatInstance(){
        if(dateTimeFormatInstance == null){
            dateTimeFormatInstance = new DateTimeFormat();
        }
        return dateTimeFormatInstance;
    }
    //Default constructor
    private DateTimeFormat() {
    }


    public void converseDateTime( String time1, String time2){
        try {
            Date dateObj1 = sdf.parse(time1);
            Log.d("Time FORMAT 1 :" , dateObj1.toString());

            Date dateObj2 = sdf.parse(time2);
            Log.d("Time FORMAT 2 : " , dateObj2.toString());

            // getTime() returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object
            long diff = dateObj2.getTime() - dateObj1.getTime();

//            _hour = 12 - Math.abs(_sleepHour - _wakeHour);
//            _min = 60 - Math.abs(_sleepMin - _wakeMin);
//
//            if(_wakeMin >= _sleepMin){
//                this.diffTime = String.valueOf(_hour)+":"+String.valueOf(_min);
//            } else {
//                this.diffTime = String.valueOf(_hour-1)+":"+String.valueOf(_min);
//            }

            int diffhours = (int) (diff / (60 * 60 * 1000));
            System.out.println("difference between hours: " + decimalFormat.format(diffhours));

            int diffmin = (int) (diff / (60 * 1000));
            System.out.println("difference between minutues: " + decimalFormat.format(diffmin));

            if(diffmin >=60){
             diffhours += (diffmin/60);
             diffmin = diffmin%60;
            }
            this.hour = diffhours;
            this.min = diffmin;

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
