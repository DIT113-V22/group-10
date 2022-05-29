package com.example.hermes;

import android.util.Log;

import org.bson.Document;

import java.text.ParseException;
import java.util.ArrayList;

import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.iterable.MongoCursor;

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
    private static ArrayList<Delivery> currentDeliveries = new ArrayList<>();

    private DatabaseManager(){
        app =  new App(new AppConfiguration.Builder(appid).build());
    }

    public static DatabaseManager getDatabaseManager()  { // implemented using the singleton pattern
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

            RealmResultTask<MongoCursor<Document>> resultTask = deliveries.find(queryFilter).iterator();
            resultTask.getAsync(task -> {
                if(task.isSuccess()){
                    currentDeliveries = new ArrayList<>();
                    MongoCursor<Document> results = task.get();
                    int counter = 0;
                    while(results.hasNext()){
                        Document doc = results.next();
                        currentDeliveries.add(new Delivery());
                        try {
                            currentDeliveries.get(counter).setDate(doc.getString("dateTime"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        currentDeliveries.get(counter).setDone(doc.getBoolean("isDone"));
                        currentDeliveries.get(counter).setReady(doc.getBoolean("isReady"));
                        currentDeliveries.get(counter).setItems((ArrayList<String>) doc.get("Items"));
                        counter++;
                    }
                    Log.v("Delivery read", "success");
                } else {
                    Log.v("Delivery read", "fail " + task.getError().toString());
                }
            });
        }
        return manager;
    }

    public static App getApp() {
        return app;
    }

    public void storeAccount(Account account){      //Stores the created account in the database
        Document queryFilter = new Document().append("userId", app.currentUser().getId());
        RealmResultTask<MongoCursor<Document>> findTask = accounts.find(queryFilter).iterator();
        findTask.getAsync(task ->{
            if(task.isSuccess()){
                MongoCursor<Document> results = task.get();
                if(results.hasNext()){
                    Document result = results.next();
                    result.append("firstName", account.getFirstName())
                            .append("lastName", account.getLastName())
                            .append("address", account.getAddress())
                            .append("dob", account.getDOB())
                            .append("postal", account.getPostalCode())
                            .append("town", account.getTown());
                    accounts.updateOne(queryFilter,result).getAsync(updateResult ->{
                        if(updateResult.isSuccess()){
                            Log.v("Update", "success");
                        } else{
                            Log.v("Update", "fail: " + updateResult.getError().toString());
                        }
                    });
                } else{
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
            } else{
                Log.v("Update", "failed:" + task.getError().toString());
            }
        });
    }

    public Account loadAccount(){
        return account;
    }

    public boolean storeDelivery(Delivery delivery){      //Stores the created delivery in the database
        if(this.getCurrentDelivery() == null) {
            Document doc = new Document("userId", app.currentUser().getId())
                    .append("dateTime", delivery.getFormatedDate())
                    //.append("time", delivery.getTime())
                    .append("isReady", delivery.getReady())
                    .append("isDone", delivery.getDone())
                    .append("Items", delivery.getItems());
            deliveries.insertOne(doc).getAsync(result -> {
                if (result.isSuccess()) {
                    Log.v("success", "success");
                } else {
                    Log.v("fail", "fail");
                }
            }); //adds the document to the database
            return true;
        } else {
            return false;
        }
    }

    public void updateCurrentDelivery(){
        Document queryFilter = new Document().append("userId", app.currentUser().getId())
                .append("isDone", false);
        RealmResultTask<MongoCursor<Document>> findTask = deliveries.find(queryFilter).iterator();
        findTask.getAsync(task ->{
            if(task.isSuccess()){
                MongoCursor<Document> results = task.get();
                if(results.hasNext()){
                    Document result = results.next();
                    result.append("isDone", true);
                    result.append("isReady", true);
                    deliveries.updateOne(queryFilter,result).getAsync(updateResult ->{
                        if(updateResult.isSuccess()){
                            Log.v("Update", "success");
                        } else{
                            Log.v("Update", "fail: " + updateResult.getError().toString());
                        }
                    });
                }
            } else{
                Log.v("Update", "failed:" + task.getError().toString());
            }
        });
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

    public ArrayList<Delivery> allDeliveries() {
        System.out.println("lenght" + currentDeliveries.size());
        return currentDeliveries;
    }


    public Delivery getCurrentDelivery(){
        for(Delivery delivery : currentDeliveries){
            System.out.println(delivery.getDone());
            if(!delivery.getDone()){
                return delivery;
            }
        }
        return null;
    }

    public void updateUser(){
        user = app.currentUser();
    }
}