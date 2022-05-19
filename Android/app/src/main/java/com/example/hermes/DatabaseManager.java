package com.example.hermes;

import static com.mongodb.client.model.Filters.eq;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import android.content.Context;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;

import io.realm.mongodb.mongo.iterable.FindIterable;

import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.mongo.MongoClient;
import com.mongodb.client.MongoClients;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.iterable.MongoCursor;
import io.realm.mongodb.mongo.MongoDatabase;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.User;

public class DatabaseManager {

    private static DatabaseManager manager;
    private static MongoDatabase database;
    private static MongoCollection<Document> accounts;
    private static MongoCollection<Document> deliveries;
    private static MongoClient client;

    private String appid = "hermesapp-mrlcy";
    private static App app;
    private static User user;

    private DatabaseManager(){
        /*
        ConnectionString connectionString = new ConnectionString("mongodb+srv://hermesApp:hermesApp@hermescluster.x7czk.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();

        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {
            database = mongoClient.getDatabase("database");
            accounts = database.getCollection("accounts", Account.class);
            deliveries = database.getCollection("deliveries", Delivery.class);
        }

         */
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
        }
        return manager;
    }

    public static App getApp() {
        return app;
    }

    public void storeAccount(Account account){      //Stores the created account in the database
        Document doc = new Document("userId", app.currentUser().getId())
                .append("firstName", account.getFirstName())
                .append("lastName", account.getLastName())
                .append("address", account.getAddress())
                .append("dob", account.getDOB())
                .append("email", account.getEmail())
                .append("password", account.getPassword());
        accounts.insertOne(doc); //adds the document to the database
    }

    public Account loadAccount(){
        Document queryFilter = new Document().append("userId", app.currentUser().getId());
        Document doc = accounts.findOne(queryFilter).get();

        //TODO add postalcode and town properly;
        return new Account(doc.getString("firstName"), doc.getString("lastName"), doc.getString("address"), "", "", doc.getString("email"), doc.getString("dob)"), doc.getString("password"));
        //return accounts.find(eq("email", email)).first(); //retrieves the account with the given accountID
    }

    public void storeDelivery(Delivery delivery){      //Stores the created delivery in the database
        Document doc = new Document("userId", app.currentUser().getId())
                .append("ID", delivery.getID())
                //.append("date", delivery.getDate())
                //.append("time", delivery.getTime())
                .append("isReady", delivery.getReady())
                .append("isDone", delivery.getDone())
                .append("Items",delivery.getItems());
        deliveries.insertOne(doc); //adds the document to the database
    }

    public Delivery loadDelivery(int deliveryID){
        Document queryFilter = new Document().append("ID", deliveryID);
        Document doc = deliveries.findOne(queryFilter).get();
        return new Delivery();
    }

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