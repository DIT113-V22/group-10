package com.example.hermes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ThanksCz extends AppCompatActivity {

    private static final String TAG = "Ďekujeme";
    private Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks_cz);

        Log.d(TAG, "onCreate: Starting.");
        Intent intent = new Intent(this, HomeScreenCz.class);

        finish = findViewById(R.id.finishOrder);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}