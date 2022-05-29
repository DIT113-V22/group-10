package com.example.hermes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingsCz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_cz);
    }

    public void changeAccountDetails(View view){
        Intent intent = new Intent(this, ChangePersonalInformationCz.class);
        startActivity(intent);
    }
    public void goBack(View view){
        Intent intent = new Intent(this, HomeScreenCz.class);
        startActivity(intent);
    }
    public void changeLanguage(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}