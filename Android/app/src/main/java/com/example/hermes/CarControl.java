package com.example.hermes;

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

public class CarControl extends AppCompatActivity {


    private AppBarConfiguration appBarConfiguration;
    private ActivityCarControlBinding binding;
    private MqttClient mqttClient;
    private  MqttClient mqttClientExternal;
    private static final  String EXTERNAL_MQTT_BROKER = "aerostun.dev";
    private static final String LOCALHOST = "10.0.2.2";
    private static final String MQTT_SERVER = "tcp://" + LOCALHOST + ":1883";
    private static final String TAG = "MqttController";
    private ImageView camera;
    private static final int QOS = 1;
    private boolean isConnected = false;
    private static final int IMAGE_WIDTH = 320;
    private static final int IMAGE_HEIGHT = 240;
    private static final String THROTTLE_CONTROL = "/smartcar/control/throttle";
    private static final String STEERING_CONTROL = "/smartcar/control/steering";

    private static final int MOVEMENT_SPEED = 70;
    private static final int IDLE_SPEED = 0;
    private static final int STRAIGHT_ANGLE = 0;
    private static final int STEERING_ANGLE = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityCarControlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       // setSupportActionBar(binding.toolbar);

       // NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_car_control);
        //appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

      /* binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

       */


        Button b = findViewById(R.id.backButton);
        Button f = findViewById(R.id.ForButton);
        Button l = findViewById(R.id.leftButton);
        Button r = findViewById(R.id.rightButton);
        b.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    move(-MOVEMENT_SPEED, STRAIGHT_ANGLE, "Going backward");
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    move(IDLE_SPEED, STRAIGHT_ANGLE, "Stopping");
                }
                return false;
            }
        });
        f.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    move(MOVEMENT_SPEED, STRAIGHT_ANGLE, "Going forward");
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    move(IDLE_SPEED, STRAIGHT_ANGLE, "Stopping");
                }
                return false;
            }
        });
        l.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    move(MOVEMENT_SPEED, -STEERING_ANGLE, "Going left");
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    move(IDLE_SPEED, STRAIGHT_ANGLE, "Stopping");
                }
                return false;
            }
        });
        r.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    move(MOVEMENT_SPEED, STEERING_ANGLE, "Going right");
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    move(IDLE_SPEED, STRAIGHT_ANGLE, "Stopping");
                }
                return false;
            }
        });
        mqttClient = new MqttClient(getApplicationContext(),MQTT_SERVER, TAG);
        camera = findViewById(R.id.camera);
        connectToMqttBroker();
        
   }

    @Override
    protected void onResume() {
        super.onResume();

        connectToMqttBroker();
    }
    @Override
    protected void onPause(){
        super.onPause();
        mqttClient.disconnect(new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Log.i(TAG,"Disconnections was successful");
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });
    }
    private void connectToMqttBroker() {
        if (!isConnected) {
            mqttClient.connect(TAG, "", new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    isConnected = true;

                    final String successfulConnection = "Connected to MQTT broker";
                    Log.i(TAG, successfulConnection);
                    Toast.makeText(getApplicationContext(), successfulConnection, Toast.LENGTH_SHORT).show();

                    mqttClient.subscribe("/smartcar/ultrasound/front", QOS, null);
                    mqttClient.subscribe("/smartcar/camera", QOS, null);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    final String failedConnection = "Failed to connect to MQTT broker";
                    Log.e(TAG, failedConnection);
                    Toast.makeText(getApplicationContext(), failedConnection, Toast.LENGTH_SHORT).show();
                }
            }, new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    isConnected = false;

                    final String connectionLost = "Connection to MQTT broker lost";
                    Log.w(TAG, connectionLost);
                    Toast.makeText(getApplicationContext(), connectionLost, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    if (topic.equals("/smartcar/camera")) {
                        final Bitmap bm = Bitmap.createBitmap(IMAGE_WIDTH, IMAGE_HEIGHT, Bitmap.Config.ARGB_8888);

                        final byte[] payload = message.getPayload();
                        final int[] colors = new int[IMAGE_WIDTH * IMAGE_HEIGHT];
                        for (int ci = 0; ci < colors.length; ++ci) {
                            final byte r = payload[3 * ci];
                            final byte g = payload[3 * ci + 1];
                            final byte b = payload[3 * ci + 2];
                            colors[ci] = Color.rgb(r, g, b);
                        }
                        bm.setPixels(colors, 0, IMAGE_WIDTH, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
                        camera.setImageBitmap(bm);
                    } else {
                        Log.i(TAG, "[MQTT] Topic: " + topic + " | Message: " + message.toString());
                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    Log.d(TAG, "Message delivered");
                }
            });
        }
    }

    public void move(int throttleSpeed, int steeringangle, String actionDescription){
        if(! isConnected){
            final String notConnected = "Not connected";
            Log.e(TAG,notConnected);
            Toast.makeText(getApplicationContext(),notConnected,Toast.LENGTH_SHORT).show();
            return;
        }
        Log.i(TAG,actionDescription);
        mqttClient.publish(THROTTLE_CONTROL,Integer.toString(throttleSpeed),QOS,null);
        mqttClient.publish(STEERING_CONTROL,Integer.toString(steeringangle),QOS,null);
    }

    /*
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
    public void stop(View v) {
        move(IDLE_SPEED, STRAIGHT_ANGLE, "Stopping");
    }

     */

    public void home(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_car_control);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}


