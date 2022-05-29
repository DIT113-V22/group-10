package com.example.hermes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ShoppingScreen extends AppCompatActivity {

    private DatabaseManager db = DatabaseManager.getDatabaseManager();
    private Button goBack;
    private TextView warning;
    private int count1 = 0;
    private int count2 = 0;
    private int count3 = 0;
    private int count4 = 0;
    private int count5 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_screen);

        Intent intent = new Intent(this, Thanks.class);

        Button addRapidTest =findViewById(R.id.rapidTest);
        Button addMask = findViewById(R.id.mask);
        Button addSanitiser = findViewById(R.id.sanitiser);
        Button addPainKiller = findViewById(R.id.painKiller);
        Button addMulti= findViewById(R.id.multivitamin);
        Button submitDelivery = findViewById(R.id.submitOrder);

        Delivery delivery = new Delivery();

        addRapidTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count1++;
                TextView text = findViewById(R.id.counter1);
                text.setText(Integer.toString(count1) + "X");
                delivery.addItem("Rapid Test");
            }
        });
        addMask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count2++;
                TextView text = findViewById(R.id.counter2);
                text.setText(Integer.toString(count2) + "X");
                delivery.addItem("Medical Mask");
            }
        });
        addSanitiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count3++;
                TextView text = findViewById(R.id.counter3);
                text.setText(Integer.toString(count3) + "X");
                delivery.addItem("Hand Sanitiser");
            }
        });
        addPainKiller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count4++;
                TextView text = findViewById(R.id.counter4);
                text.setText(Integer.toString(count4) + "X");
                delivery.addItem("Pain Killer");
            }
        });
        addMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count5++;
                TextView text = findViewById(R.id.counter5);
                text.setText(Integer.toString(count5) + "X");
                delivery.addItem("Miltivitamin");
            }
        });
        submitDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                warning = findViewById(R.id.onlyOneOrderWarning);
                if(db.storeDelivery(delivery)){
                    warning.setVisibility(View.INVISIBLE);
                    startActivity(intent);
                } else {
                    warning.setVisibility(View.VISIBLE);
                }
            }
        });

        goBack = (Button) findViewById(R.id.shoppingBack);
        goBack.setOnClickListener(view1 -> {
            Intent intentBack = new Intent(this, HomeScreen.class);
            startActivity(intentBack);
        });
    }
}