package com.example.hermes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TermsandConditions extends AppCompatActivity {


    private static final String TAG = "termsAndConditions";
    private Button termsBack;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.termsandconditions);
        Log.d(TAG, "onCreate: Starting.");

        termsBack = (Button) findViewById(R.id.termsBackB);
        termsBack.setOnClickListener(view1 -> {
            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
        });



    }
}
