package com.example.hermes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ControlSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_selection);
        Button joystickButton = findViewById(R.id.JoystickButton);
        joystickButton.setOnClickListener(view -> selectJoystick());

        Button controlButton = findViewById(R.id.buttonControl);
        controlButton.setOnClickListener(view -> selectButtons());
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