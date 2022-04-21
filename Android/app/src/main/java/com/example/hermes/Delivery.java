package com.example.hermes;

import java.time.*;

public class Delivery {
    public String date;
    public String time;
    public String customerID; //Change later to customer object

    public Delivery(String customerID){
        this.customerID = customerID;
        this.date = LocalDate.now().toString();
        this.time = LocalTime.now().toString();
    }
}
