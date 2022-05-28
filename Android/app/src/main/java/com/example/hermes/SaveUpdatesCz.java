package com.example.hermes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;

public class SaveUpdatesCz extends AppCompatActivity {

    EditText firstName, lastName, dob, street, postal, town;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_updates_cz);

        Realm.init(this);
        DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();
        Account account = databaseManager.loadAccount();

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        dob = (EditText) findViewById(R.id.changeDOB);
        street = findViewById(R.id.address);
        postal = (EditText) findViewById(R.id.postalcode);
        town = (EditText) findViewById(R.id.town);

        firstName.setText(account.getFirstName());
        lastName.setText(account.getLastName());
        dob.setText(account.getDOB());
        street.setText(account.getAddress());
        postal.setText(account.getPostalCode());
        town.setText(account.getTown());
    }

    public void savePersonalInformation(View view) {
        Account account = new Account(firstName.getText().toString(), lastName.getText().toString(), street.getText().toString(), postal.getText().toString(), town.getText().toString(), dob.getText().toString());
        DatabaseManager manager = DatabaseManager.getDatabaseManager();
        manager.storeAccount(account);
        Intent intent = new Intent(this, SettingsCz.class);
        startActivity(intent);
    }

    public void goBack(View view){
        Intent intent = new Intent(this, ChangePersonalInformationCz.class);
        startActivity(intent);
    }
}