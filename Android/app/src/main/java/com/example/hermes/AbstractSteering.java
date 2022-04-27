package com.example.hermes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.hermes.ui.MqttClient;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public abstract class AbstractSteering extends AppCompatActivity {

    protected ImageView camera ;
    private MqttClient mqttClient;
    private boolean isConnected = false;
    protected AppBarConfiguration appBarConfiguration;
    private static final String THROTTLE_CONTROL = "/smartcar/control/throttle";
    private static final String STEERING_CONTROL = "/smartcar/control/steering";
    private static int IMAGE_WIDTH = 320;
    private static int IMAGE_HEIGHT = 240;
    private static final int QOS = 1;
    private static final String LOCALHOST = "10.0.2.2";
    protected static final String MQTT_SERVER = "tcp://" + LOCALHOST + ":1883";
    protected static final String TAG = "MqttController";
    private static boolean joystickenable = false;



    protected void setCamera(ImageView camera){

        this.camera = camera;
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
    protected void connectToMqttBroker() {
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

    public void move(int throttleSpeed, int steeringangle){
        if(! isConnected){
            final String notConnected = "Not connected";
            Log.e(TAG,notConnected);
            Toast.makeText(getApplicationContext(),notConnected,Toast.LENGTH_SHORT).show();
            return;
        }
        mqttClient.publish(THROTTLE_CONTROL,Integer.toString(throttleSpeed),QOS,null);
        mqttClient.publish(STEERING_CONTROL,Integer.toString(steeringangle),QOS,null);
    }

    protected void MqttConnect(ImageView camera){
        mqttClient = new MqttClient(getApplicationContext(),MQTT_SERVER, TAG);

        int cameraId = camera.getId();
            this.camera = findViewById(cameraId);

        connectToMqttBroker();
    }

    public void home(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    protected void setImageWidth(int width){
        IMAGE_WIDTH = width;
    }

    protected void setImageHeight(int height) {
        IMAGE_HEIGHT = height;
    }

    protected void initialiseMqttClient(Context context){
        this.mqttClient = new MqttClient(context, LOCALHOST, TAG);
    }
}
