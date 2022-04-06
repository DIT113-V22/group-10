#include <Smartcar.h>

const int TRIGGER_PIN           = 6; // D6
const int ECHO_PIN              = 7; // D7
const unsigned int MAX_DISTANCE = 100;

const int fSpeed   = 70;  // 70% of the full speed forward



ArduinoRuntime arduinoRuntime;
SR04 front(arduinoRuntime, TRIGGER_PIN, ECHO_PIN, MAX_DISTANCE);

BrushedMotor leftMotor(arduinoRuntime, smartcarlib::pins::v2::leftMotorPins);
BrushedMotor rightMotor(arduinoRuntime, smartcarlib::pins::v2::rightMotorPins);
DifferentialControl control(leftMotor, rightMotor);

SimpleCar car(control);

void drive()
{
car.setSpeed(fSpeed);
car.setAngle(0);
}

void setup()
{
    Serial.begin(9600);
}

void loop()
{
  if(front.getDistance() <= 50)
  {
    drive();
  }
  else {
    car.setSpeed(0);
  }
  Serial.println(front.getDistance());
    delay(100);
}
