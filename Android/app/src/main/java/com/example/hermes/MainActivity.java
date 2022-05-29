package com.example.hermes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hermes.databinding.ActivityMainBinding;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    public static SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sp = getSharedPreferences("login", MODE_PRIVATE);

        Realm.init(this); //Initialise mongo realm
        DatabaseManager manager = DatabaseManager.getDatabaseManager();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);

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