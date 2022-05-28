package com.example.hermes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hermes.databinding.ActivitySaveUpdatesBinding;

import io.realm.Realm;

public class SaveUpdates<firstName> extends AppCompatActivity {

    EditText firstName, lastName, dob, street, postal, town;

    private AppBarConfiguration appBarConfiguration;
    private ActivitySaveUpdatesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySaveUpdatesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Realm.init(this);
        DatabaseManager databaseManager = DatabaseManager.getDatabaseManager();
        Account account = databaseManager.loadAccount();

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        dob = (EditText) findViewById(R.id.changeDOB);
        street = findViewById(R.id.address);
        postal = (EditText) findViewById(R.id.postalcode);
        town = (EditText) findViewById(R.id.town);

        firstName.setText(account.getFirstName());
        lastName.setText(account.getLastName());
        dob.setText(account.getDOB());
        street.setText(account.getAddress());
        postal.setText(account.getPostalCode());
        town.setText(account.getTown());
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_save_updates2);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void savePersonalInformation(View view) {
        Account account = new Account(firstName.getText().toString(), lastName.getText().toString(), street.getText().toString(), postal.getText().toString(), town.getText().toString(), dob.getText().toString());
        DatabaseManager manager = DatabaseManager.getDatabaseManager();
        manager.storeAccount(account);
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void goBack(View view){
        Intent intent = new Intent(this, ChangePersonalInformation.class);
        startActivity(intent);
    }
}