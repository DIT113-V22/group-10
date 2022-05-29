package com.example.hermes.ui;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.internal.ConnectActionListener;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;




// Adapted from https://github.com/DIT112-V21/smartcar-mqtt-controller/blob/main/android/SmartcarMqttController/app/src/main/java/platis/solutions/smartcarmqttcontroller/MqttClient.java
// and https://www.alibabacloud.com/help/en/iot-platform/latest/use-the-paho-mqtt-android-client

public class MqttClient {

    private MqttAndroidClient mMqttAndroidClient;
    private Context context;
    private String clientId;

    public MqttClient(Context context, String serverUrl, String clientId) {
        mMqttAndroidClient = new MqttAndroidClient(context, serverUrl, clientId);
        this.context = context;
        this.clientId = clientId;
    }

    public void connect(String username, String password, IMqttActionListener connectionCallback, MqttCallback clientCallback) {
        mMqttAndroidClient.setCallback(clientCallback);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        try {
            mMqttAndroidClient.connect(options, null, connectionCallback);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void disconnect(IMqttActionListener disconnectionCallback) {
        try {
            mMqttAndroidClient.disconnect(null, disconnectionCallback);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(String topic, int qos, IMqttActionListener subscriptionCallback) {
        try {
            mMqttAndroidClient.subscribe(topic, qos, null, subscriptionCallback);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String topic, String message, int qos, IMqttActionListener publishCallback) {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(message.getBytes());
        mqttMessage.setQos(qos);

        try {
            mMqttAndroidClient.publish(topic, mqttMessage, null, publishCallback);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


}
