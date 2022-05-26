package com.example.hermes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Thanks extends AppCompatActivity {

    private static final String TAG = "Thanks";
    private Button finish;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanking_layout);
        Log.d(TAG, "onCreate: Starting.");
        Intent intent = new Intent(this, HomeScreen.class);

        finish = findViewById(R.id.finishOrder);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });


    }
}
