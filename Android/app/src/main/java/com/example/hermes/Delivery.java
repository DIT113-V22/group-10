package com.example.hermes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Delivery {
    private int ID;
    private Date date;
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

    public String getDate(){
        String[] splitted = date.toString().split(" ");
        return splitted[5] + " " + splitted[1] + " " + splitted[2];
    }

    public void setDate(String yyyymmdd_hhmmss) throws ParseException {
        date = new SimpleDateFormat("yyyyMMdd hh:mm:ss").parse(yyyymmdd_hhmmss);
    }

    public void setDate(){
        date = new Date();
    }

    public String getTime() {
        String[] splitted = date.toString().split(" ");
        return splitted[3];
    }

    public int getID() { return ID; }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean getReady() { return isReady; }
    public void setReady(boolean value) { this.isReady = value; }
    public boolean getDone() { return this.isDone; }
    public void setDone(boolean value) { this.isDone = value; }

    public int idGenerator(){
        return (int) this.customerID.hashCode() * this.date.hashCode();
    }

    public static final Comparator<Delivery> byDate = new Comparator<Delivery>() {
        @Override
        public int compare(Delivery delivery1, Delivery delivery2) {
            return delivery1.compareDeliveryDates(delivery2);
        }
    };

    public static final Comparator<Delivery> byReverseDate = new Comparator<Delivery>() {
        @Override
        public int compare(Delivery delivery1, Delivery delivery2) {
            return delivery2.compareDeliveryDates(delivery1);
        }
    };

    public int compareDeliveryDates(Delivery anotherDelivery){
        Date anotherDate = anotherDelivery.date;
        return this.date.compareTo(anotherDate);
    }
}