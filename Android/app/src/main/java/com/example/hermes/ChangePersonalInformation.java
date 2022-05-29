package com.example.hermes;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.hermes.databinding.ActivityChangePersonalInformationBinding;

import io.realm.Realm;

public class ChangePersonalInformation extends AppCompatActivity {

    private ActivityChangePersonalInformationBinding binding;
    private TextView fullName;
    private TextView DOB;
    private TextView address;
    private TextView postalCode;
    private TextView town;
    private TextView password;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChangePersonalInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Realm.init(this);
        DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();
        Account account = databaseManager.loadAccount();

        fullName = (TextView) findViewById(R.id.fullname);
        DOB = (TextView) findViewById(R.id.changeDOB);
        address = (TextView) findViewById(R.id.address);
        postalCode = (TextView) findViewById(R.id.postalcode);
        town = (TextView) findViewById(R.id.town);
        password = (TextView) findViewById(R.id.password);

        fullName.setText(account.getFirstName() + " " + account.getLastName());
        DOB.setText(account.getDOB());
        address.setText(account.getAddress());
        postalCode.setText(account.getPostalCode());
        town.setText(account.getTown());
        password.setText("********");



    }
    public void savePersonalInformation(View view){
        Intent intent = new Intent(this, SaveUpdates.class);
        startActivity(intent);
    }

    public void goBack(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

}

