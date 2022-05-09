package com.example.hermes;

import static com.mongodb.client.model.Filters.eq;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;

import com.mongodb.client.FindIterable;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.ArrayList;

public class DatabaseManager {
    private static DatabaseManager manager;
    MongoDatabase database;

    private DatabaseManager(){
        try {                                                       //attempts to connect to the database

            ConnectionString connectionString = new ConnectionString("mongodb+srv://hermesApp:hermesApp@hermescluster.x7czk.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .build();
            MongoClient mongoClient = MongoClients.create(settings);
            database = mongoClient.getDatabase("test");

        } catch(Exception e){                                       //if connection fails, prints error message
            e.printStackTrace();
        }

        MongoCollection collection = database.getCollection("test");
        Document doc = new Document();
        doc.append("id", "thing");
        collection.insertOne(doc);

        System.out.println("fin");
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
        MongoCollection<Document> accounts = database.getCollection("accounts"); //retrieves the collection from the database called "accounts", or creates it if it doesn't exist
        Document databaseAccount = new Document();
        databaseAccount.append("Email", account.getEmail())
                .append("Account", account); //adds key value pair of the accountID and account to the document
        accounts.insertOne(databaseAccount); //adds the document to the database

    }

    public Account loadAccount(String email){
        MongoCollection<Document> accounts = database.getCollection("accounts");
        Document account = accounts.find(eq("Email", email)).first(); //retrieves the account with the given accountID
        return (Account) account.get("Account"); //returns the account stored in the database
    }


    public void storeDelivery(Delivery delivery){      //Stores the created delivery in the database
        MongoCollection<Document> deliveries = database.getCollection("deliveries"); //retrieves the collection from the database called "deliveries", or creates it if it doesn't exist
        Document databaseDelivery = new Document();
        databaseDelivery.append("ID", delivery.getID())
                .append("Delivery", delivery); //adds key value pair of the deliveryID and account to the document
        deliveries.insertOne(databaseDelivery); //adds the document to the database

    }

    public Delivery loadDelivery(int deliveryID){
        MongoCollection<Document> deliveries = database.getCollection("deliveries");
        Document delivery = deliveries.find(eq("ID", deliveryID)).first(); //retrieves the delivery with the given deliveryID
        return (Delivery) delivery.get("Delivery"); //returns the delivery stored in the database
    }

    public ArrayList<Delivery> allDeliveries() {
        ArrayList<Delivery> result = new ArrayList<>();
        MongoCollection<Document> deliveries = database.getCollection("deliveries");
        FindIterable<Document> iterable = deliveries.find();
        MongoCursor<Document> cursor = iterable.iterator();
        try {
            while(cursor.hasNext()) {
                Document deliveryDocument = cursor.next();
                // Rest of the code here.
                Delivery deliveryObject = (Delivery) deliveryDocument.get("Delivery"); //extract the actual delivery object from the document
                result.add(deliveryObject); //add the delivery object to the array
            }
        } finally {
            cursor.close();
        }

        return result;
    }

}
