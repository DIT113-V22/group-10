package com.example.hermes.ui;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.internal.ConnectActionListener;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;


// Adapted from https://github.com/DIT112-V21/smartcar-mqtt-controller/blob/main/android/SmartcarMqttController/app/src/main/java/platis/solutions/smartcarmqttcontroller/MqttClient.java
// and https://www.alibabacloud.com/help/en/iot-platform/latest/use-the-paho-mqtt-android-client

public class MqttClient {

    private MqttAndroidClient mMqttAndroidClient;
    private final String TAG ="myActivity";

    //Get the Mqtt arguments in the constructor
    public MqttClient(Context context, String serverUrl, String clientId){
        // Create a MqttAndroidClient object
     mMqttAndroidClient = new MqttAndroidClient(context, serverUrl,clientId);
    }
    public void connect(String username, String password, IMqttActionListener connectionCallback, MqttCallback clientCallback) {
        //Configure the callback
        mMqttAndroidClient.setCallback(clientCallback);
        // Create a MqttConnectOptions object and configure the username and password
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());
       // options.setAutomaticReconnect(true);
        options.setCleanSession(true);
         //Connecting to the car using Mqtt
        try {
            mMqttAndroidClient.connect(options, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    //Report success
                    Log.i(TAG,"Connection was successful");

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                   Log.i(TAG,"Connection failed");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String topic, String message, int qos) {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(message.getBytes());
        mqttMessage.setQos(qos);

        try {
            mMqttAndroidClient.publish(topic, mqttMessage, qos, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.i(TAG, "publish was successful");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.i(TAG, "publish failed");
                }
            });
        }
        catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
