package com.example.hermes;

import android.os.Build;

import java.time.LocalDate;
import java.time.LocalTime;

public class Delivery {
    private int ID;
    private String date;
    private String time;
    private String customerID; //Change later to customer object
    private boolean isReady;
    private boolean isDone;


    public Delivery(){
        this.customerID = customerID;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.date = LocalDate.now().toString();
            this.time = LocalTime.now().toString();
        }
        this.ID = idGenerator();
        this.isReady = false;
        this.isDone = false;
    }

    public Delivery(String customerID, String date, String time){

        this.customerID = customerID;
        this.date = date;
        this.time = time;
        this.ID = idGenerator();
    }



    public String getCustomerID(){
        return customerID;
    }
    public void setCustomerID(String ID){
        if (customerID == null){
            this.customerID = ID;
        }
    }
    public String getDate(){
        return date;
    }
    public String getTime(){ return time; }
    public int getID() { return ID; }
    public boolean getReady() { return isReady; }
    public void setReady(boolean value) { this.isReady = value; }
    public boolean getDone() { return this.isDone; }
    public void setDone(boolean value) { this.isDone = value; }

    public int idGenerator(){
        //return (int) this.time.hashCode() * this.date.hashCode();
        return 1;
    }
}