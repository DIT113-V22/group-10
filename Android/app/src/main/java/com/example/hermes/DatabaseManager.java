package com.example.hermes;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

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

}
