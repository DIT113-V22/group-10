package com.example.hermes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class TermsAndConditionsCz extends AppCompatActivity {

    private static final String TAG = "termsAndConditions";
    private Button termsBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions_cz);
        Log.d(TAG, "onCreate: Starting.");

        termsBack = (Button) findViewById(R.id.termsBackB);
        termsBack.setOnClickListener(view1 -> {
            Intent intent = new Intent(this, HomeScreenCz.class);
            startActivity(intent);
        });
    }
}