package com.example.hermes;

import android.os.Bundle;

import com.example.hermes.ui.MqttClient;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hermes.databinding.ActivityJoystickBinding;
import com.zerokol.views.joystickView.JoystickView;

public class Joystick extends CarControl{

    private AppBarConfiguration appBarConfiguration;
    private ActivityJoystickBinding binding;
    private TextView angleTextView;
    private TextView powerTextView;
    private TextView directionTextView;
    private JoystickView joystick;
    private MqttClient mqttClient;
    private ImageView camera;
    private static final String LOCALHOST = "10.0.2.2";
    private static final String MQTT_SERVER = "tcp://" + LOCALHOST + ":1883";
    private static final String TAG = "MqttController";
    private static final int QOS = 1;
    private boolean isConnected = false;
    private static final String THROTTLE_CONTROL = "/smartcar/control/throttle";
    private static final String STEERING_CONTROL = "/smartcar/control/steering";

    private static final int MOVEMENT_SPEED = 70;
    private static final int IDLE_SPEED = 0;
    private static final int STRAIGHT_ANGLE = 0;
    private static final int STEERING_ANGLE = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick);
        mqttClient = new MqttClient(getApplicationContext(),MQTT_SERVER, TAG);
        camera = findViewById(R.id.Joystick_camera);
        angleTextView = (TextView) findViewById(R.id.angleTextView);
        powerTextView = (TextView) findViewById(R.id.powerTextView);
        directionTextView = (TextView) findViewById(R.id.directionTextView);
        //Referencing also other views
        joystick = (JoystickView) findViewById(R.id.joystickView);

        binding = ActivityJoystickBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_joystick);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


      /*  binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

       */
        angleTextView = (TextView) findViewById(R.id.angleTextView);
        powerTextView = (TextView) findViewById(R.id.powerTextView);
        directionTextView = (TextView) findViewById(R.id.directionTextView);
        //Referencing also other views
        joystick = (JoystickView) findViewById(R.id.joystickView);

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


                        break;
                    case JoystickView.FRONT_RIGHT:
                        directionTextView.setText(R.string.front_right_lab);
                        move(MOVEMENT_SPEED, STRAIGHT_ANGLE, "Going forward");
                        break;
                    case JoystickView.RIGHT:
                        directionTextView.setText(R.string.right_lab);
                        break;
                    case JoystickView.RIGHT_BOTTOM:
                        directionTextView.setText(R.string.right_bottom_lab);
                        break;
                    case JoystickView.BOTTOM:
                        directionTextView.setText(R.string.bottom_lab);
                        break;
                    case JoystickView.BOTTOM_LEFT:
                        directionTextView.setText(R.string.bottom_left_lab);
                        break;
                    case JoystickView.LEFT:
                        directionTextView.setText(R.string.left_lab);
                        break;
                    case JoystickView.LEFT_FRONT:
                        directionTextView.setText(R.string.left_front_lab);
                        break;
                    default:
                        directionTextView.setText(R.string.center_lab);
                }
            }
        }, JoystickView.DEFAULT_LOOP_INTERVAL);
    }

    public void goForward(View v){
        move(MOVEMENT_SPEED, STRAIGHT_ANGLE, "Going forward");
    }
    public void goBackward(View v){
        move(-MOVEMENT_SPEED, STRAIGHT_ANGLE, "Going backward");
    }
    public void goLeft(View v){
        move(MOVEMENT_SPEED, -STEERING_ANGLE,"Going left");
    }
    public void goRight(View v){
        move(MOVEMENT_SPEED, STEERING_ANGLE, "Going right");
    }
    public void goForwardRight(View view){
        move(MOVEMENT_SPEED,STEERING_ANGLE,"Going forward right");
    }
    public void goForwardLeft(View view){
        move(MOVEMENT_SPEED,STEERING_ANGLE,"Going forward left");
    }







    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_joystick);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}