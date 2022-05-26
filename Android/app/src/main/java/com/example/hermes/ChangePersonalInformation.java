package com.example.hermes;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.hermes.databinding.ActivityChangePersonalInformationBinding;

import io.realm.Realm;

public class ChangePersonalInformation extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityChangePersonalInformationBinding binding;
    private TextView fullName;
    private TextView DOB;
    private TextView address;
    private TextView postalCode;
    private TextView town;
    private TextView password;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChangePersonalInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Realm.init(this);
        DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();
        Account account = databaseManager.loadAccount();

        fullName = (TextView) findViewById(R.id.fullname);
        DOB = (TextView) findViewById(R.id.changeDOB);
        address = (TextView) findViewById(R.id.address);
        postalCode = (TextView) findViewById(R.id.postalcode);
        town = (TextView) findViewById(R.id.town);
        password = (TextView) findViewById(R.id.password);

        fullName.setText(account.getFirstName() + " " + account.getLastName());
        DOB.setText(account.getDOB());
        address.setText(account.getAddress());
        postalCode.setText(account.getPostalCode());
        town.setText(account.getTown());
        password.setText("********");


       /* setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_change_personal_information);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });




    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_change_personal_information);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();

        */


    }
    public void savePersonalInformation(View view){
        Intent intent = new Intent(this, SaveUpdates.class);
        startActivity(intent);
    }

    public void goBack(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

}

