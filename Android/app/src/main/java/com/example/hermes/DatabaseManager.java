package com.example.hermes;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class DatabaseManager {
    MongoDatabase database;

    public DatabaseManager(){
        try {                                                       //attempts to connect to the database
            MongoClient client = MongoClients.create();
            database = client.getDatabase("database");
        } catch(Exception e){                                       //if connection fails, prints error message
            e.printStackTrace();
        }
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
