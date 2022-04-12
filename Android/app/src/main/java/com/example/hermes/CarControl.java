package com.example.hermes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hermes.databinding.ActivityCarControlBinding;
import com.example.hermes.ui.MqttClient;

public class CarControl extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCarControlBinding binding;
    private MqttClient mqttClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityCarControlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_car_control);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

       /* binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        */
   }



   public void goForward(View v){
        // drive(MOVEMENT_SPEED, STRAIGHT_ANGLE, "Going forward");

   }
   public void goBackward(View v){
        // drive(-MOVEMENT_SPEED, STRAIGHT-ANGLE, "Going backward");
   }
   public void goLeft(View v){
        //drive(MOVEMENT_SPEED, -STEERING_ANGLE,"Going left");
   }
   public void goRight(View v){
        //drive(MOVEMENT_SPEED, STEERING_ANGLE, "Going right");
   }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_car_control);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}