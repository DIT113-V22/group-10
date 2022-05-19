package com.example.hermes;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.hermes.databinding.ActivityMainBinding;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this); //Initialise mongo realm

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Notification","Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
/*
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        //Button startButton = findViewById(R.id.button2);


 */


        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
    }

    public void start(View view) {
        //Intent intent = new Intent(this, CarControl.class);
        //startActivity(intent);

        Intent intent = new Intent(this, ControlSelection.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Notification")
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("HERMES")
                .setContentText("Your car is ready! Please take over control.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(01, builder.build());
    }

//<<<<<<< HEAD
    public void showDelivery(View view) {
        //To implement
//=======
    }
    public void openLogin(View view){
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
    }

    public void createAccount(View view){
        DatabaseManager manager = DatabaseManager.getDatabaseManager();
        //manager.storeAccount(new Account("n", "n", "n", "n", "n", "n"));
        Intent intent = new Intent(this, RegisterAccount.class);
        startActivity(intent);
//>>>>>>> origin/master
    }


}