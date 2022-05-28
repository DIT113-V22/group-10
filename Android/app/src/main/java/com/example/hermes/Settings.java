package com.example.hermes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.hermes.databinding.ActivitySettingsBinding;

public class Settings extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    public void changeAccountDetails(View view){
        Intent intent = new Intent(this, ChangePersonalInformation.class);
        startActivity(intent);
    }
    public void goBack(View view){
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
    public void changeLanguage(View view){
        Intent intent = new Intent(this, SettingsCz.class);
        startActivity(intent);
    }
}

