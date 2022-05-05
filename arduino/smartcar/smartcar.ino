#include <vector>

#include <MQTT.h>
#include <WiFi.h>
#ifdef __SMCE__
#include <OV767X.h>
#endif

#include <Smartcar.h>

MQTTClient mqtt;
WiFiClient net;

const char ssid[] = "***";
const char pass[] = "****";

ArduinoRuntime arduinoRuntime;
BrushedMotor leftMotor(arduinoRuntime, smartcarlib::pins::v2::leftMotorPins);
BrushedMotor rightMotor(arduinoRuntime, smartcarlib::pins::v2::rightMotorPins);
DifferentialControl control(leftMotor, rightMotor);
SimpleCar car(control);
bool obstacleAhead = false;
bool messageFlag = false;

#include <Smartcar.h>

const auto oneSecond = 1000UL;
#ifdef __SMCE__
const auto triggerPin = 6;
const auto echoPin = 7;
const auto mqttBrokerUrl = "127.0.0.1";
#else
const auto triggerPin = 33;
const auto echoPin = 32;
const auto mqttBrokerUrl = "192.168.0.40";
#endif
const int fSpeed = 50;    // 50% of the full speed forward
const int bSpeed = -40;   // 40% of the full speed backward
const int lDegrees = -75; // degrees to turn left
const int rDegrees = 75;  // degrees to turn right
const auto maxDistance = 400;
const auto SIDE_LEFT_PIN = 1;
const auto SIDE_RIGHT_PIN = 2;
SR04 front(arduinoRuntime, triggerPin, echoPin, maxDistance);
GP2Y0A02 leftSide(arduinoRuntime, SIDE_LEFT_PIN);
GP2Y0A02 rightSide(arduinoRuntime, SIDE_RIGHT_PIN);


std::vector<char> frameBuffer;

bool detectObstacle(){
  auto distance = front.getDistance();
  if(distance <= 100 && distance != 0)
    return true;
  auto leftDistance = leftSide.getDistance();
  auto rightDistance = rightSide.getDistance();
  if(leftDistance > 0 || rightDistance > 0)
    return true;

  return false;
  }
  
  String obstacleDetectionMessage(){
    String msg = "obstacle warning";
    if (obstacleAhead){
        return msg;
    }
    return "";
   }

void drive(int carSpeed){
  if(obstacleAhead && carSpeed>0){car.setSpeed(0);}
  else{car.setSpeed(carSpeed);
  }
  
}

void setup() {
  Serial.begin(9600);
#ifdef __SMCE__
  Camera.begin(QVGA, RGB888, 15);
  frameBuffer.resize(Camera.width() * Camera.height() * Camera.bytesPerPixel());
#endif

  WiFi.begin(ssid, pass);
  mqtt.begin(mqttBrokerUrl, 1883, net);

  Serial.println("Connecting to WiFi...");
  auto wifiStatus = WiFi.status();
  while (wifiStatus != WL_CONNECTED && wifiStatus != WL_NO_SHIELD) {
    Serial.println(wifiStatus);
    Serial.print(".");
    delay(1000);
    wifiStatus = WiFi.status();
  }


  Serial.println("Connecting to MQTT broker");
  while (!mqtt.connect("arduino", "public", "public")) {
    Serial.print(".");
    delay(1000);
  }

  mqtt.subscribe("/smartcar/control/#", 1);
  mqtt.onMessage([](String topic, String message) {
    if (topic == "/smartcar/control/throttle") {
      drive(message.toInt());
    } else if (topic == "/smartcar/control/steering") {
      car.setAngle(message.toInt());
  
    } else {
      Serial.println(topic + " " + message);
    }
  });
}

void loop() {
 // detectObstacle();
  if (mqtt.connected()) {
    mqtt.loop();
    const auto currentTime = millis();
#ifdef __SMCE__
    static auto previousFrame = 0UL;
    if (currentTime - previousFrame >= 65) {
      previousFrame = currentTime;
      Camera.readFrame(frameBuffer.data());
      mqtt.publish("/smartcar/camera", frameBuffer.data(), frameBuffer.size(),
                   false, 0);
    }
#endif
    obstacleAhead = detectObstacle();
    static auto previousTransmission = 0UL;
    if (currentTime - previousTransmission >= oneSecond) {
      previousTransmission = currentTime;
      const auto distance = String(front.getDistance());
      mqtt.publish("/smartcar/ultrasound/front", distance);
      if(messageFlag!= obstacleAhead){
      mqtt.publish("/smartcar/control/obstacleMsg", String(obstacleDetectionMessage()));
      messageFlag=obstacleAhead;
      }
    }
  }
#ifdef __SMCE__
  // Avoid over-using the CPU if we are running in the emulator
  delay(1);
#endif
}
