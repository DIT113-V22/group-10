package com.example.hermes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class ControlSelection extends AppCompatActivity {

    private Button backB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_selection);
        Button joystickButton = findViewById(R.id.JoystickButton);
        joystickButton.setOnClickListener(view -> selectJoystick());

        Button controlButton = findViewById(R.id.buttonControl);
        controlButton.setOnClickListener(view -> selectButtons());

        Intent homeIntent = new Intent(this, HomeScreen.class);
        backB = findViewById(R.id.buttonControlBack);
        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(homeIntent);
            }
        });
    }
    public void selectButtons() {
        Intent buttonControlIntent = new Intent(this, CarControl.class);
        startActivity(buttonControlIntent);
    }

    public void selectJoystick() {
        Intent joystickIntent = new Intent(this, Joystick.class);
        startActivity(joystickIntent);
    }

}