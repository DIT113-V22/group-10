package com.example.hermes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hermes.databinding.ActivityHomeScreenBinding;

public class HomeScreen extends AppCompatActivity {

    //private AppBarConfiguration appBarConfiguration;
    private ActivityHomeScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       //BottomNavigationView bottomNav = findViewById(R.id.nav_view2);
       //bottomNav.setOnNavigationItemSelectedListener(navListener);
/*
        BottomNavigationView navView = findViewById(R.id.nav_view2);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_screen);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //NavigationUI.setupWithNavController(binding.navView, navController);


 */

        DatabaseManager manager = DatabaseManager.getDatabaseManager();
        manager.storeDelivery(new Delivery());

    }
/*
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.bottom_navi_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.bottom_navi_notification:
                            selectedFragment = new NotificationsFragment();
                            break;
                        case R.id.bottom_navi_dashboard:
                            selectedFragment = new DashboardFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_home_screen, selectedFragment).commit();
                    return true;
                }

    };
/*
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_screen);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

 */



    public void openSettings(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}