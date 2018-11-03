package com.example.lab203_29.healthy;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormat {

    private static DateTimeFormat dateTimeFormatInstance;
    private String date;
    private String format = "dd-MMM-yy";

    SimpleDateFormat sdf = new SimpleDateFormat(format);

    public static DateTimeFormat getDateTimeFormatInstance(){
        if(dateTimeFormatInstance == null){
            dateTimeFormatInstance = new DateTimeFormat();
        }
        return dateTimeFormatInstance;
    }
    //Default constructor
    private DateTimeFormat() {
    }



    //getter, setter

    public String getDate() {
        return date;
    }

    public void setDate(String date) {

        Date dateObj = null;
        try {
            dateObj = sdf.parse(date);
            Log.d("DATE FORMAT:" , dateObj.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int dateObjDate = dateObj.getDate();
        if(dateObjDate%10 == dateObjDate){
            this.date = "0" + date;
        }else{
            this.date = date;
        }

    }
}
