# group-10: Hermes Project
<p align="center">
 <img width="460" height="300" src="https://user-images.githubusercontent.com/95288826/169854101-7b736ac2-b5ae-41c7-a980-2c0f84910af6.png"
>
</p>


## ![icons8-find-50](https://user-images.githubusercontent.com/95288826/169863151-8a7cc546-9110-421c-a58d-f314e9738e14.png) What is Hermes project?
 
Hermes app is an android app, which enables the users to order a delivery, take control over a delivery car and guide it to their house doors. This leads to a reliable delivering of an order without the need for human contact. The cusotmers shall create an account and login to their accounts to be able to see their deliveries and take control over a delivery car. This way the system will provide security to prevent wrong customers take contorl over a car and ensure that the cargo has been delivered safely, which will then be confirmed by the receiver.

#### Main functionalities:
* Create accounts
* Login to accounts
* Go to shopping page and create a delivery
* Store created accounts in the database
* Store created deliveries in the database
* Steer a delivery car via buttons or Joystick control
* Preview the ongoing and completed deliver lists
* Sort the deliveries based on different filters
* Get feedback about the user's experinece with the app
* Store feedback(reviews) in the database
* Allow the users to update their profiles


## ![icons8-help-48](https://user-images.githubusercontent.com/95288826/169860837-2b237f3f-5e56-4449-b92f-fb003000db13.png) Why did we make it
The goal with the delivery car is to allow customers to get medical orders right to their door, avoiding human interactions. This will decrease infections due to human contact and also ease the workload of delivery personnel, by making it easier to locate the proper delivery site, and by passing on the responsibility of final navigation on to the customer.

## ![icons8-work-64](https://user-images.githubusercontent.com/95288826/169863588-acf6a026-03e5-4f45-b5d8-e59eb2910a73.png) How did we make it
 
The system consists of two parts, a delivery car and a smartphone application, connecting to each other via mosquitto broker. The app will notify the customer when their order is ready for the final delivery, at which point they will be able to take control of the car through the app, and steer it to their desired delivery point, by using their phone to steer, and navigating through a camera attached to the car. The customer must of course be logged in into the application, to confirm their identity, and prevent miss-delivery of packages. After making deliveries by customers, the app will store the delivery information in a database. The customer's accounts and their feedbacks about the app are also stored in the database. We have used MongoDB database for stroing the data. If you had any questions about the inner works of the database, please check the [Get Started](https://github.com/DIT113-V22/group-10/wiki/Get-Started) on Wiki page.
## ![icons8-clapperboard-48](https://user-images.githubusercontent.com/95288826/169866347-6d6882ad-fabd-48d9-8af0-394597e2e177.png) Project demo
* [Demo](https://youtu.be/Md8BHDQSFDE)
## ![icons8-book-stack-64](https://user-images.githubusercontent.com/95288826/169868931-c062e87f-4d79-4a07-a3ec-6e965de978df.png) Documents
* User Manual
* [Use case diagram](https://github.com/DIT113-V22/group-10/wiki/Use-Case-Diagram)
* [Class diagram](https://github.com/DIT113-V22/group-10/wiki/Class-Diagram)
## ![icons8-tools-64](https://user-images.githubusercontent.com/95288826/169868576-7a9f1868-598f-4dd8-bcc4-990196ec8004.png) Technologies & resources
* Arduino IDE
* Android Studio
* SMCE Emulator
* Mosquitto borker
* MongoDB database
* Zerokol JoystickView library
* Smartcar shield library
* github
* Java 
* C++
* Json
* Diagrams.net
## ![icons8-broken-computer-50](https://user-images.githubusercontent.com/95288826/169870606-79afd88d-8d2d-49ac-a70c-87d25689ee3c.png) Installation
### Get started
For more information about installation of the app go to [Get Started](https://github.com/DIT113-V22/group-10/wiki/Get-Started) on wiki page.
## ![icons8-pixel-man-64](https://user-images.githubusercontent.com/95288826/169869731-943f6d3c-f965-4809-ae08-e884f56e74ff.png)Contributors
* [Yasamin Fazelidehkordi](https://github.com/YasaminFazeli)
* [Julia Ayvazian](https://github.com/juliaayvazian)
* [Amin Mahmoudifard](https://github.com/aminmahmoam)
* [Emrik Dunvlad](https://github.com/Kirme1)
* [Daniël J Coetzer](https://github.com/DanielJCoetzer)
* [Patrik Samcenko](https://github.com/nihilisss)


