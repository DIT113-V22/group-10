package com.example.hermes;

import android.util.Log;

import io.realm.mongodb.mongo.iterable.FindIterable;

import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.iterable.MongoCursor;
import io.realm.mongodb.mongo.MongoDatabase;

import org.bson.Document;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.User;

public class DatabaseManager {

    private static DatabaseManager manager;
    private static MongoDatabase database;
    private static MongoCollection<Document> accounts;
    private static MongoCollection<Document> deliveries;
    private static MongoCollection<Document> reviews;
    private static MongoClient client;

    private String appid = "hermesapp-mrlcy";
    private static App app;
    private static User user;
    private static Account account = new Account("","","","","","");

    private DatabaseManager(){
        app =  new App(new AppConfiguration.Builder(appid).build());
    }

    public static DatabaseManager getDatabaseManager(){ // implemented using the singleton pattern
        if (manager == null){
            synchronized(DatabaseManager.class){
                if (manager == null){
                    manager = new DatabaseManager();
                }
            }
        }

        user = app.currentUser();
        if(user != null) {
            client = user.getMongoClient("mongodb-atlas");
            database = client.getDatabase("database");
            accounts = database.getCollection("accounts");
            deliveries = database.getCollection("deliveries");
            reviews = database.getCollection("reviews");

            Document queryFilter = new Document().append("userId", app.currentUser().getId());
            accounts.findOne(queryFilter).getAsync(result -> {
                if(result.isSuccess()) {
                    Document doc = result.get();
                    if(doc!=null) {
                        account.setFirstName(doc.getString("firstName"));
                        account.setLastName(doc.getString("lastName"));
                        account.setAddress(doc.getString("address"));
                        account.setPostalCode(doc.getString("postal"));
                        account.setTown(doc.getString("town"));
                        account.setDOB(doc.getString("dob"));
                    }
                    //return new Account(doc.getString("firstName"), doc.getString("lastName"), doc.getString("address"), doc.getString("postal"), doc.getString("town"), doc.getString("dob)"));
                    Log.v("Data", "success");
                }else{
                    Log.v("Data", "fail");
                }
            });
        }
        return manager;
    }

    public static App getApp() {
        return app;
    }

    public void storeAccount(Account account){      //Stores the created account in the database
//        Document queryFilter = new Document().append("userId", app.currentUser().getId());
//        accounts.deleteOne(queryFilter).getAsync(result -> {
//            if(result.isSuccess()){
//                Log.v("Delete", "success");
//            }else{
//                Log.v("Delete", "fail");
//            }
//        });
        Document doc = new Document("userId", app.currentUser().getId())
                .append("firstName", account.getFirstName())
                .append("lastName", account.getLastName())
                .append("address", account.getAddress())
                .append("dob", account.getDOB())
                .append("postal", account.getPostalCode())
                .append("town", account.getTown());
        accounts.insertOne(doc).getAsync(result -> {
            if (result.isSuccess()) {
                Log.v("Insertion", "success");
            } else {
                Log.v("Insertion", "fail");
            }
        }); //adds the document to the database
    }

    public Account loadAccount(){

        return account;
    }

    public void storeDelivery(Delivery delivery){      //Stores the created delivery in the database
        Document doc = new Document("userId", app.currentUser().getId())
                .append("date", delivery.getDate())
                .append("time", delivery.getTime())
                .append("isReady", delivery.getReady())
                .append("isDone", delivery.getDone())
                .append("Items",delivery.getItems());
        deliveries.insertOne(doc).getAsync(result -> {
            if (result.isSuccess()) {
                Log.v("success", "success");
            } else {
                Log.v("fail", "fail");
            }
        }); //adds the document to the database
    }

    public void storeFeedback(FeedBack feedback){
        Document doc = new Document("userId", app.currentUser().getId())
                .append("Rating", feedback.getRate())
                .append("Message", feedback.getText());
        reviews.insertOne(doc).getAsync(result -> {
            if (result.isSuccess()) {
                Log.v("success", "success");
            } else {
                Log.v("fail", "fail");
            }
        }); //adds the document to the database
    }

   /* public Delivery loadDelivery(int deliveryID){
        Document queryFilter = new Document().append("ID", deliveryID);
        Document doc = deliveries.findOne(queryFilter).get();
        return new Delivery();
    }

    */

    public ArrayList<Delivery> allDeliveries() {

        ArrayList<Delivery> result = new ArrayList<>();
        FindIterable<Document> iterable = deliveries.find();
        RealmResultTask<MongoCursor<Document>> cursor = iterable.iterator();
        /*
        try {
            while(cursor.hasNext()) {
                Delivery delivery = cursor.next();
                result.add(delivery); //add the delivery object to the array
            }
        } finally {
            cursor.close();
        }
         */
        return result;
    }

    public void updateUser(){
        user = app.currentUser();
    }
}