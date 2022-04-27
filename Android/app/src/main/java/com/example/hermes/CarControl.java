package com.example.hermes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hermes.databinding.ActivityCarControlBinding;
import com.example.hermes.ui.MqttClient;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

// Adapted from https://github.com/DIT112-V21/smartcar-mqtt-controller.git for mqtt connection

public class CarControl extends AbstractSteering {

    private ActivityCarControlBinding binding;
    private static final int MOVEMENT_SPEED = 70;
    private static final int IDLE_SPEED = 0;
    private static final int STRAIGHT_ANGLE = 0;
    private static final int STEERING_ANGLE = 50;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_car_control);
        super.onCreate(savedInstanceState);
        initialiseMqttClient(getApplicationContext());

        binding = ActivityCarControlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button b = findViewById(R.id.backButton);
        Button f = findViewById(R.id.ForButton);
        Button l = findViewById(R.id.leftButton);
        Button r = findViewById(R.id.rightButton);
        b.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    move(-MOVEMENT_SPEED, STRAIGHT_ANGLE);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    move(IDLE_SPEED, STRAIGHT_ANGLE);
                }
                return false;
            }
        });
        f.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    move(MOVEMENT_SPEED, STRAIGHT_ANGLE);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    move(IDLE_SPEED, STRAIGHT_ANGLE);
                }
                return false;
            }
        });
        l.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    move(MOVEMENT_SPEED, -STEERING_ANGLE);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    move(IDLE_SPEED, STRAIGHT_ANGLE);
                }
                return false;
            }
        });
        r.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    move(MOVEMENT_SPEED, STEERING_ANGLE);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    move(IDLE_SPEED, STRAIGHT_ANGLE);
                }
                return false;
            }
        });
        MqttConnect();
   }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_car_control);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}


