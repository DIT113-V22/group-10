package com.example.hermes;

import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.hermes.databinding.ActivityJoystickBinding;

public class Joystick extends AbstractSteering{

/*  
    private ActivityJoystickBinding binding;
    private JoystickView joystick;
    private ImageView Joystick_camera;
    private TextView joystickTextView;
    private static final int MOVEMENT_SPEED = 70;
    private static final int STRAIGHT_ANGLE = 0;
    private static final int STEERING_ANGLE = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick);
        Joystick_camera = findViewById(R.id.Joystick_camera);
        joystickTextView= findViewById(R.id.Joystick_text);
        setImageHeight(240);
        setImageWidth(320);
        setCamera(Joystick_camera);
        setTextView(joystickTextView);
        initialiseMqttClient(getApplicationContext());
       // binding = ActivityJoystickBinding.inflate(getLayoutInflater());
       // setContentView(binding.getRoot());
        MqttConnect(Joystick_camera);
        joystick =  findViewById(R.id.joystickView);

        //Event listener that always returns the variation of the angle in degrees, motion power in percentage and direction of movement
        joystick.setOnJoystickMoveListener(new JoystickView.OnJoystickMoveListener() {

            @Override
            public void onValueChanged(int angle, int power, int direction) {
                // TODO Auto-generated method stub
                switch (direction) {
                    case JoystickView.FRONT: {
                        move(MOVEMENT_SPEED, STRAIGHT_ANGLE);
                    }
                        break;
                    case JoystickView.FRONT_RIGHT:
                        move(MOVEMENT_SPEED, -STEERING_ANGLE);
                        break;
                    case JoystickView.RIGHT: {
                        move(MOVEMENT_SPEED, -STEERING_ANGLE);
                    }
                        break;
                    case JoystickView.RIGHT_BOTTOM:
                        move(-MOVEMENT_SPEED, -STEERING_ANGLE);
                        break;
                    case JoystickView.BOTTOM: {
                        move(-MOVEMENT_SPEED, STRAIGHT_ANGLE);
                    }
                        break;
                    case JoystickView.BOTTOM_LEFT:
                        move(-MOVEMENT_SPEED,  STEERING_ANGLE);
                        break;
                    case JoystickView.LEFT: {
                        move(MOVEMENT_SPEED,  STEERING_ANGLE);
                    }
                        break;
                    case JoystickView.LEFT_FRONT:
                        move(MOVEMENT_SPEED, STEERING_ANGLE);
                        break;
                    default:
                        move(0, 0);
                }
            }
        }, JoystickView.DEFAULT_LOOP_INTERVAL);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_joystick);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

 */
}