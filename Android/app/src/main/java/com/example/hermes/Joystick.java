package com.example.hermes;

import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hermes.databinding.ActivityJoystickBinding;
import com.zerokol.views.joystickView.JoystickView;

public class Joystick extends AbstractSteering{

    private AppBarConfiguration appBarConfiguration;
    private ActivityJoystickBinding binding;
    private TextView angleTextView;
    private TextView powerTextView;
    private TextView directionTextView;
    private JoystickView joystick;
    private ImageView Joystick_camera;
    private static final int MOVEMENT_SPEED = 70;
    private static final int STRAIGHT_ANGLE = 0;
    private static final int STEERING_ANGLE = 50;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick);
        Joystick_camera = findViewById(R.id.Joystick_camera);
        setImageHeight(240);
        setImageWidth(320);
        setCamera(Joystick_camera);

        angleTextView = (TextView) findViewById(R.id.angleTextView);
        powerTextView = (TextView) findViewById(R.id.powerTextView);
        directionTextView = (TextView) findViewById(R.id.directionTextView);
        joystick = (JoystickView) findViewById(R.id.joystickView);

        initialiseMqttClient(getApplicationContext());

        binding = ActivityJoystickBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Event listener that always returns the variation of the angle in degrees, motion power in percentage and direction of movement
        joystick.setOnJoystickMoveListener(new JoystickView.OnJoystickMoveListener() {

            @Override
            public void onValueChanged(int angle, int power, int direction) {
                // TODO Auto-generated method stub
                angleTextView.setText(" " + String.valueOf(angle) + "°");
                powerTextView.setText(" " + String.valueOf(power) + "%");
                switch (direction) {
                    case JoystickView.FRONT:
                        directionTextView.setText(R.string.front_lab);
                        move(MOVEMENT_SPEED, STRAIGHT_ANGLE, "Going forward");

                        break;
                    case JoystickView.FRONT_RIGHT:
                        directionTextView.setText(R.string.front_right_lab);
                        break;
                    case JoystickView.RIGHT:
                        directionTextView.setText(R.string.right_lab);
                        move(MOVEMENT_SPEED, STEERING_ANGLE, "Going right");
                        break;
                    case JoystickView.RIGHT_BOTTOM:
                        directionTextView.setText(R.string.right_bottom_lab);
                        break;
                    case JoystickView.BOTTOM:
                        directionTextView.setText(R.string.bottom_lab);
                        move(-MOVEMENT_SPEED, STRAIGHT_ANGLE, "Going backward");
                        break;
                    case JoystickView.BOTTOM_LEFT:
                        directionTextView.setText(R.string.bottom_left_lab);
                        break;
                    case JoystickView.LEFT:
                        directionTextView.setText(R.string.left_lab);
                        move(MOVEMENT_SPEED, -STEERING_ANGLE, "Going left");
                        break;
                    case JoystickView.LEFT_FRONT:
                        directionTextView.setText(R.string.left_front_lab);
                        break;
                    default:
                        directionTextView.setText(R.string.center_lab);
                }
            }
        }, JoystickView.DEFAULT_LOOP_INTERVAL);

        MqttConnect();
    }




    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_joystick);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}