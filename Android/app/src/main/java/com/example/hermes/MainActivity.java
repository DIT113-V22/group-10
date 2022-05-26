package com.example.hermes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hermes.databinding.ActivityMainBinding;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this); //Initialise mongo realm

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
    }



    public void showDelivery(View view) {
        //To implement
    }
    public void openLogin(View view){
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
    }

    public void createAccount(View view){
        DatabaseManager manager = DatabaseManager.getDatabaseManager();
        //manager.storeAccount(new Account("n", "n", "n", "n", "n", "n"));
        Intent intent = new Intent(this, RegisterAccount.class);
        startActivity(intent);
    }
    public void setDelivery(View view){
        Intent intent = new Intent(this, ShoppingScreen.class);
        startActivity(intent);
    }


}