package com.example.hermes;

public class Account {
    private String firstName;
    private String lastName;
    private String address;
    private String postalCode;
    private String town;
    private String DOB;
    private String language;

    public Account(String firstName, String lastName, String address, String postalCode, String town, String DOB){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postalCode = postalCode;
        this.town = town;
        this.DOB = DOB;
    }


    public String getFirstName() {
        return firstName; }

    public void setFirstName(String firstName){
        this.firstName = firstName; }

    public String getLastName() {
        return lastName; }

    public void setLastName(String lastName){
        this.lastName = lastName; }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public String getPostalCode(){ return postalCode; }

    public void setPostalCode(String postalCode){ this.postalCode = postalCode;}

    public String getTown(){ return town; }

    public void setTown(String town){ this.town = town;}

    public String getDOB(){
        return DOB;
    }

    public void setDOB(String DOB){
        this.DOB = DOB;
    }
}
