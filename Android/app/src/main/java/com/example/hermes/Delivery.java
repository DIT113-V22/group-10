package com.example.hermes;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.Date;

public class Delivery {
    private int ID;
    private Date date;
    private LocalTime time;
    private String customerID; //Change later to customer object
    private boolean isReady;
    private boolean isDone;

    /*
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

     */

    public String getCustomerID(){
        return customerID;
    }
    public void setCustomerID(String ID){
        if (customerID == null){
            this.customerID = ID;
        }
    }
    public Date getDate(){
        return date;
    }
    public LocalTime getTime(){ return time; }
    public int getID() { return ID; }
    public boolean getReady() { return isReady; }
    public void setReady(boolean value) { this.isReady = value; }
    public boolean getDone() { return this.isDone; }
    public void setDone(boolean value) { this.isDone = value; }

    public int idGenerator(){
        return (int) this.customerID.hashCode() * this.time.hashCode() * this.date.hashCode();
    }

    public static final Comparator<Delivery> byDate = new Comparator<Delivery>() {
        @Override
        public int compare(Delivery delivery1, Delivery delivery2) {
            return delivery1.compareDelivery(delivery2);
        }
    };

    public static final Comparator<Delivery> byReverseDate = new Comparator<Delivery>() {
        @Override
        public int compare(Delivery delivery1, Delivery delivery2) {
            return delivery2.compareDelivery(delivery1);
        }
    };

    public int compareDelivery(Delivery anotherDelivery){
        Date anotherDate = anotherDelivery.date;
        LocalTime anotherTime = anotherDelivery.time;
        int date = this.date.compareTo(anotherDate);
        int time = this.time.compareTo(anotherTime);

        if(date != 0)
            return date;

        return time;
    }
}