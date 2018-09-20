package com.example.lab203_29.healthy.weight;

/**
 * Created by LAB203_37 on 27/8/2561.
 */

public class Weight {
    private String date;
    int weight;
    private String status;

    public  Weight(){}



    public  Weight(String date, int weight, String status){
        this.date = date;
        this.weight = weight;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
