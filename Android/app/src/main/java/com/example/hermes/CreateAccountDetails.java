package com.example.hermes;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hermes.databinding.ActivityCreateAccountDetailsBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccountDetails extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCreateAccountDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCreateAccountDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

     /*   setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_create_account_details);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

      */
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_create_account_details);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public boolean validateName(String name){
        Pattern checkPattern = Pattern.compile("[a-zA-Z]");
        Matcher match = checkPattern.matcher(name);
        return match.find();
    }
    /* public boolean validateAdress(){

    }

     */
}