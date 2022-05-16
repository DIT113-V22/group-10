package com.example.hermes;

import android.os.Build;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Delivery {
    private int ID;
    private String date;
    private String time;
    private String customerID; //Change later to customer object
    private boolean isReady;
    private boolean isDone;
    private ArrayList<String> items;


    public Delivery(String customerID){
        this.customerID = customerID;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.date = LocalDate.now().toString();
            this.time = LocalTime.now().toString();
        }
        this.ID = idGenerator();
        this.isReady = false;
        this.isDone = false;
        this.items= new ArrayList<>();
    }





   public Delivery(String customerID,String date, String time){
        this.customerID = customerID;
        this.date = date;
        this.time = time;
        this.ID=idGenerator();
        this.isReady=false;
        this.isDone=false;
        this.items= new ArrayList<>();
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

    public void setDate(String Date){
        this.date=Date;
    }
    public void setTime(String Time){
        this.time=Time;
    }
    public String getTime(){ return time; }
    public  int getID() { return ID; }
    public boolean getReady() { return isReady; }
    public void setReady(boolean value) { this.isReady = value; }
    public boolean getDone() { return this.isDone; }
    public void setDone(boolean value) { this.isDone = value; }
   public void addItem(String ItemName){
        items.add(ItemName);
   }
   public ArrayList <String> getItems(){
        return  items;
   }

    public int idGenerator(){
        return (int) this.customerID.hashCode() * this.time.hashCode() * this.date.hashCode();
    }
}