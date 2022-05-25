package com.example.hermes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.hermes.ui.home.HomeFragment;


public class ShoppingScreen extends AppCompatActivity {

    private DatabaseManager db = DatabaseManager.getDatabaseManager();
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
        String Date= date.getText().toString();
        String Time=time.getText().toString();
        String Id = id.getText().toString();
        Delivery delivery= new Delivery("1", Date,Time);

        addRapidTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delivery.addItem("Rapid Test "+"\n");
            }
        });
        addGelofen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delivery.addItem("Gelofen"+"\n");
            }
        });
        addAlvedon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delivery.addItem("Alvedon"+"\n");
            }
        });
        addSpray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delivery.addItem("Spray"+"\n");
            }
        });
        addSyrup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delivery.addItem("Syrup"+"\n");
            }
        });
        submitDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 db.storeDelivery(delivery);
               /* Intent home = new Intent(this,HomeFragment.class);
                startActivity(home);

                */



            }
        });



    }
}