package com.example.lab203_29.healthy.sleep;

import android.content.ContentValues;
import android.util.Log;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sleep {

    private int id;
    private String date;
    private String sleepTime;
    private String wakeUpTime;
    private String diffTime;


    ContentValues _row = new ContentValues();

    //Default Constructor
    public Sleep() {
    }

    //Constructor
    public Sleep(int id,String sleep, String wake, String date) {
        this.id = id;
        this.sleepTime = sleep;
        this.wakeUpTime = wake;
        this.date = date;

        //calculate different time between sleep and wake
        setDiffTime(sleep, wake);
    }


    /*****************************************************
     *   intent: แปลงวันที่จาก type String to date Obj.         *
     *   pre-condition: ส่งค่า sleep wake date type String   *
     *   post-condition: set parameter to  ContentValues *
     ****************************************************/
    public void setContent(String sleep, String wake, String date) {
        String format = "dd-MMM-yyyy hh:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date dateObj =  sdf.parse(date+" "+sleep);
            Log.d("dateObj Time format : " , dateObj.toString());
            this._row.put("sleep", sleep);
            this._row.put("wake", wake);
            this._row.put("process_date",dateObj.getTime());
            Log.d("dateObj.getTime(): " , String.valueOf(dateObj.getTime()));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    /*****************************************************
     *   intent:set content values to parameter _row     *
     *   pre-condition: ต้อง set content values            *
     *   post-condition: save _row to SQL                *
     *   return: content values                          *
     ****************************************************/
    public ContentValues getContent() {
        return _row;
    }

    //getter, setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getWakeUpTime() {
        return wakeUpTime;
    }

    public void setWakeUpTime(String wakeUpTime) {
        this.wakeUpTime = wakeUpTime;
    }


    public String getDiffTime() {
        return diffTime;
    }

    /****************************
     *1 minute = 60 seconds     *
     *1 hour = 60 x 60 = 3600   *
     *1 day = 3600 x 24 = 86400 *
     ****************************/
    public void setDiffTime(String sleepTime, String wakeUpTime) {
        int _hour;
        int _min;
        String[] _sleepArray = sleepTime.split(":");
        String[] _wakeArray = wakeUpTime.split(":");
        Log.d("Sleep Hour: Min" , _sleepArray[0] +" : "+ _sleepArray[1] );
        Log.d("wake Hour: Min" , _wakeArray[0] +" : "+ _wakeArray[1] );

        int intSleep = (Integer.parseInt(_sleepArray[0]))*3600 + (Integer.parseInt(_sleepArray[1])*60);
        int intWake = (Integer.parseInt(_wakeArray[0])*3600) + (Integer.parseInt(_wakeArray[1])*60);

        String hour,min;
        if(intSleep > intWake){
            Log.d("Sleep","more than wake");
            hour = String.valueOf(Math.round(86400 - (intSleep - intWake)) / 3600);
            min = String.valueOf(Math.round((86400 - (intSleep - intWake)) % 3600) / 60);
        }else {
            Log.d("Sleep","less than wake");
            hour = String.valueOf(Math.round((intWake - intSleep)) / 3600);
            min = String.valueOf(Math.round(((intWake - intSleep)) % 3600) / 60);
        }

        this.diffTime = hour + ":" + min;
    }

}
