package com.example.hermes;

import java.time.LocalDate;
import java.time.LocalTime;

public class Delivery {
    private final int ID;
    private final String date;
    private final String time;
    private final String customerID; //Change later to customer object
    private boolean isReady;
    private boolean isDone;

    public Delivery(String customerID){
        this.customerID = customerID;
        this.date = LocalDate.now().toString();
        this.time = LocalTime.now().toString();
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
        return (int) this.customerID.hashCode() * this.time.hashCode() * this.date.hashCode();
    }
}