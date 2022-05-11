package com.example.hermes;

import static com.mongodb.client.model.Filters.eq;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;

import com.mongodb.client.FindIterable;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;

public class DatabaseManager {
    private static DatabaseManager manager;
    private static MongoDatabase database;
    private static MongoCollection<Account> accounts;
    private static MongoCollection<Delivery> deliveries;

    private DatabaseManager(){
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
    }

    public static DatabaseManager getDatabaseManager(){ // implemented using the singleton pattern
        if (manager == null){
            synchronized(DatabaseManager.class){
                if (manager == null){
                    manager = new DatabaseManager();
                }
            }
        }
        return manager;
    }

    public void storeAccount(Account account){      //Stores the created account in the database
        accounts.insertOne(account); //adds the document to the database

    }

    public Account loadAccount(String email){
        return accounts.find(eq("email", email)).first(); //retrieves the account with the given accountID
    }


    public void storeDelivery(Delivery delivery){      //Stores the created delivery in the database
        deliveries.insertOne(delivery); //adds the document to the database
    }

    public Delivery loadDelivery(int deliveryID){
        return deliveries.find(eq("ID", deliveryID)).first(); //retrieves the delivery with the given deliveryID
    }

    public ArrayList<Delivery> allDeliveries() {

        ArrayList<Delivery> result = new ArrayList<>();
        FindIterable<Delivery> iterable = deliveries.find();
        MongoCursor<Delivery> cursor = iterable.iterator();
        try {
            while(cursor.hasNext()) {
                Delivery delivery = cursor.next();
                result.add(delivery); //add the delivery object to the array
            }
        } finally {
            cursor.close();
        }
        return result;
    }
}