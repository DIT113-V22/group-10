package com.example.hermes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeScreenCz extends AppCompatActivity {
    private Button allDeliveriesB;
    private Button termsAndConditions;
    private Button shopping;
    private Button feedback;
    private TextView currentDelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_cz);

        DatabaseManager db = DatabaseManager.getDatabaseManager();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Notification","Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        allDeliveriesB = (Button) findViewById(R.id.allDeliveriesB);
        allDeliveriesB.setOnClickListener(view1 -> {
            Intent intent = new Intent(this, DisplayDeliveriesCz.class);
            startActivity(intent);
        });

        termsAndConditions = (Button) findViewById(R.id.termsB);
        termsAndConditions.setOnClickListener(view1 -> {
            Intent intent = new Intent(this, TermsAndConditionsCz.class);
            startActivity(intent);
        });

        shopping = (Button) findViewById(R.id.ShoppingB);
        shopping.setOnClickListener(view1 -> {
            Intent intent = new Intent(this, ShoppingScreenCz.class);
            startActivity(intent);
        });

        feedback = (Button) findViewById(R.id.feedbackButton);
        feedback.setOnClickListener(view1 -> {
            Intent intent = new Intent(this, FeedBackViewCz.class);
            startActivity(intent);
        });

        currentDelivery = findViewById(R.id.textView35);
        Delivery delivery = db.getCurrentDelivery();
        if(delivery != null){
            currentDelivery.setText(delivery.itemList());
        } else {
            currentDelivery.setText("????dn?? nedoru??en?? objedn??vka");
        }
    }

    public void start(View view) {

        DatabaseManager manager = DatabaseManager.getDatabaseManager();
        manager.updateCurrentDelivery();

        Intent intent = new Intent(this, ControlSelectionCz.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Notification")
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("HERMES")
                .setContentText("Va??e auto je p??ipraveno! Pros??m p??evezm??te si jej..")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(01, builder.build());
    }


    public void openSettings(View view){
        Intent intent = new Intent(this, SettingsCz.class);
        startActivity(intent);
    }

}