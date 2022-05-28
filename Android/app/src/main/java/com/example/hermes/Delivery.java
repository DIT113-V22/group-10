package com.example.hermes;

import android.os.Build;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class Delivery {
    private Date date;
    private boolean isReady;
    private boolean isDone;
    private ArrayList<String> items;

    public Delivery(){
        Date date = new Date();
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.date = date;
        //}
        this.isReady = false;
        this.isDone = false;
        this.items= new ArrayList<>();
    }

    public Delivery(String date) throws ParseException {
        Date d = new SimpleDateFormat("yyyyMMdd HH:mm:ss", Locale.CHINA).parse(date);
        this.date = d;
    }

    public String getDate(){
        String[] split = date.toString().split(" ");
        return split[5] + " " + split[1] + " " + split[2];
    }
    
    public String getFormatedDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss", Locale.CHINA);
        return dateFormat.format(this.date);
    }

    public String getTime() {
        String[] split = date.toString().split(" ");
        return split[3];
    }

    public String getDateTime(){
        return date.toString();
    }

    public void setDate(String yyyymmdd_hhmmss) throws ParseException {
        date = new SimpleDateFormat("yyyyMMdd HH:mm:ss", Locale.CHINA).parse(yyyymmdd_hhmmss);
    }

    public void addItem(String ItemName){
        items.add(ItemName);
    }

    public ArrayList <String> getItems(){
        return  items;
    }

    public boolean getReady() { return isReady; }

    public void setReady(boolean value) { this.isReady = value; }

    public boolean getDone() { return this.isDone; }

    public void setItems(ArrayList<String> items){
        this.items = items;
    }

    public void setDone(boolean value) { this.isDone = value; }

    public int idGenerator(String customerID, Date date){
        return customerID.hashCode() * date.hashCode();
    }

    public String itemList(){
        String result = "";
        for(String item : items){
            result += item + ", ";
        }
        return result;
    }

    @NonNull
    @Override
    public String toString(){
        return this.date.toString();
    }

    public static final Comparator<Delivery> byOldest = Comparator.comparing(delivery -> delivery.date);

    public static final Comparator<Delivery> byNewest = (delivery1, delivery2) -> delivery2.date.compareTo(delivery1.date);

}