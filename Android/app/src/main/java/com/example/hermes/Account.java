package com.example.hermes;

public class Account {
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String DOB;
    private String password;

    public Account(String firstName, String lastName, String address, String email, String DOB, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.DOB = DOB;
        this.password = password;
    }
    public Account(String email, String password){
        this.email = email;
        this.password = password;
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
    public String getDOB(){
        return DOB;
    }
    public void setDOB(String DOB){
        this.DOB = DOB;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
