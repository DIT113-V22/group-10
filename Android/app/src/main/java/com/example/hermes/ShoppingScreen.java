package com.example.hermes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hermes.ui.home.HomeFragment;


public class ShoppingScreen extends AppCompatActivity {
 private Delivery delivery = new Delivery();
   // private DatabaseManager db = DatabaseManager.getDatabaseManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_screen);
        Button addRapidTest =findViewById(R.id.button9);
        Button addGelofen = findViewById(R.id.button13);
        Button addAlvedon = findViewById(R.id.button14);
        Button addSpray= findViewById(R.id.button15);
        Button addSyrup= findViewById(R.id.button16);
        Button submitDelivery = findViewById(R.id.button18);
        EditText id = findViewById(R.id.editTextTextPassword4);
        EditText date = findViewById(R.id.editTextDate2);
        EditText time = findViewById(R.id.editTextTime);
        addRapidTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delivery.addItem("Rapid Test ");
            }
        });
        addGelofen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delivery.addItem("Gelofen");
            }
        });
        addAlvedon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delivery.addItem("Alvedon");
            }
        });
        addSpray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delivery.addItem("Spray");
            }
        });
        addSyrup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delivery.addItem("Syrup");
            }
        });
        submitDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delivery.setCustomerID(id.getText().toString());
                delivery.setDate(date.getText().toString());
                delivery.setTime(date.getText().toString());
               // db.storeDelivery(delivery);
               /*Intent home = new Intent(this,HomeFragment.class);
                startActivity(home);

                */

            }
        });



    }
}