package com.example.hermes;

import java.time.LocalDate;
import java.time.LocalTime;

public class Delivery {
    private final int ID;
    private final String date;
    private final String time;
    private final String customerID; //Change later to customer object

    public Delivery(String customerID){
        this.ID = idGenerator();
        this.customerID = customerID;
        this.date = LocalDate.now().toString();
        this.time = LocalTime.now().toString();
    }

    public Delivery(String customerID, String date, String time){
        this.ID = idGenerator();
        this.customerID = customerID;
        this.date = date;
        this.time = time;
    }

    public String getCustomerID(){
        return customerID;
    }

    public String getDate(){
        return date;
    }

    public String getTime(){
        return time;
    }

    public int idGenerator(){
        return (int) customerID.hashCode() * time.hashCode() * date.hashCode();
    }
}