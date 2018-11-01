package com.example.lab203_29.healthy.sleep;

import android.content.ContentValues;
import android.provider.BaseColumns;

public class Sleep {

    //Database
    public static final String DATABASE_NAME = "sleeps.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE = "sleep";

    public class Column{
        public static final String ID = BaseColumns._ID;
        public static final String DATE = "date";
        public static final String SLEEP_TIME = "sleep_time";
        public static final String WAKE_UP_TIME = "wake_up_time";
        public static final String TOTAL_TIME = "total_time";
    }

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
    public Sleep(String sleep, String wake, String date) {
        this.sleepTime = sleep;
        this.wakeUpTime = wake;
        this.date = date;

        setDiffTime(sleep, wake);
    }
    //ContentValues
    public void setContent(String sleep, String wake, String date) {
        this._row.put("sleep", sleep);
        this._row.put("wake", wake);
        this._row.put("date", date);
    }

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

    public void setDiffTime(String sleepTime, String wakeUpTime) {
        int _hour;
        int _min;


        String[] _sleep = sleepTime.split(":");
        int _sleepHour = Integer.parseInt(_sleep[0])%12;
        int _sleepMin = Integer.parseInt(_sleep[1]);

        String[] _wake = sleepTime.split(":");
        int _wakeHour = Integer.parseInt(_wake[0])%12;
        int _wakeMin = Integer.parseInt(_wake[1]);

        _hour = 12 - Math.abs(_sleepHour - _wakeHour);
        _min = 60 - Math.abs(_sleepMin - _wakeMin);

        if(_wakeMin >= _sleepMin){
            this.diffTime = String.valueOf(_hour)+":"+String.valueOf(_min);
        } else {
            this.diffTime = String.valueOf(_hour-1)+":"+String.valueOf(_min);
        }

    }

}
