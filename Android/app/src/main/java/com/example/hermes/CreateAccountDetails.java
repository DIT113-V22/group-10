package com.example.hermes;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
    private ImageView nameWarning;
    private TextView nameWarningText;
    private ImageView lastNameWarning;
    private TextView lastNameWarningText;
    private ImageView DOBWarning;
    private TextView DOBWarningText;
    private ImageView streetNameWarning;
    private TextView streetNameWarningText;
    private ImageView postalCodeWarning;
    private TextView postalCodeWarningText;
    private ImageView townWarning;
    private TextView townWarningText;
    private EditText name;
    private EditText lastName;
    private EditText DOB;
    private EditText streetName;
    private EditText postalCode;
    private EditText town;





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
        if(match.find()){
            nameWarning = (ImageView) findViewById(R.id.imageView7);
            nameWarning.setVisibility(View.INVISIBLE);
            nameWarningText = (TextView) findViewById(R.id.textView7);
            nameWarningText.setVisibility(View.INVISIBLE);
            return true;
        }else{
            nameWarning = (ImageView) findViewById(R.id.imageView7);
            nameWarning.setVisibility(View.VISIBLE);
            nameWarningText = (TextView) findViewById(R.id.textView7);
            nameWarningText.setVisibility(View.VISIBLE);
            return false;
        }

    }
    public boolean validateLastName(String lastName){
        Pattern checkPattern = Pattern.compile("[a-zA-Z]");
        Matcher match = checkPattern.matcher(lastName);
        if(match.find()){
            lastNameWarning = (ImageView) findViewById(R.id.imageView13);
            lastNameWarning.setVisibility(View.INVISIBLE);
            lastNameWarningText = (TextView) findViewById(R.id.textView8);
            lastNameWarningText.setVisibility(View.INVISIBLE);
            return true;
        }else {
            lastNameWarning = (ImageView) findViewById(R.id.imageView13);
            lastNameWarning.setVisibility(View.VISIBLE);
            lastNameWarningText = (TextView) findViewById(R.id.textView8);
            lastNameWarningText.setVisibility(View.VISIBLE);
            return false;
        }
    }
    public boolean validateStreetName(String streetName) {
        Pattern checkPattern = Pattern.compile("[a-zA-Z]");
        Matcher match = checkPattern.matcher(streetName);
        if (match.find()) {
            streetNameWarning = (ImageView) findViewById(R.id.imageView12);
            streetNameWarning.setVisibility(View.INVISIBLE);
            streetNameWarningText = (TextView) findViewById(R.id.textView10);
            streetNameWarningText.setVisibility(View.INVISIBLE);
            return true;
        } else {
            streetNameWarning = (ImageView) findViewById(R.id.imageView12);
            streetNameWarning.setVisibility(View.VISIBLE);
            streetNameWarningText = (TextView) findViewById(R.id.textView10);
            streetNameWarningText.setVisibility(View.VISIBLE);
            return false;

        }

    }

    public boolean validateDOB(String DOB){
        Pattern checkPattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
        Matcher match = checkPattern.matcher(DOB);
        if (match.find()) {
            DOBWarning = (ImageView) findViewById(R.id.imageView11);
            DOBWarning.setVisibility(View.INVISIBLE);
            DOBWarningText = (TextView) findViewById(R.id.textView9);
            DOBWarningText.setVisibility(View.INVISIBLE);
            return true;
        } else {
            DOBWarning = (ImageView) findViewById(R.id.imageView11);
            DOBWarning.setVisibility(View.VISIBLE);
            DOBWarningText = (TextView) findViewById(R.id.textView9);
            DOBWarningText.setVisibility(View.VISIBLE);
            return false;
        }

    }
    public boolean validatePostalCode(String postalCode) {
        Pattern checkPattern = Pattern.compile("[0-9]");
        Matcher match = checkPattern.matcher(postalCode);
        if (match.find() && postalCode.length() == 5) {
            postalCodeWarning = (ImageView) findViewById(R.id.imageView10);
            postalCodeWarning.setVisibility(View.INVISIBLE);
            postalCodeWarningText = (TextView) findViewById(R.id.textView11);
            postalCodeWarningText.setVisibility(View.INVISIBLE);
            return true;
        } else {
            postalCodeWarning = (ImageView) findViewById(R.id.imageView10);
            postalCodeWarning.setVisibility(View.VISIBLE);
            postalCodeWarningText = (TextView) findViewById(R.id.textView11);
            postalCodeWarningText.setVisibility(View.VISIBLE);
            return false;
        }
    }
    public boolean validateTown(String town) {

        if (town.equals("Göteborg") || town.equals("Gothenburg") || town.equals("Borås") || town.equals("Malmö") || town.equals("Stockholm")) {
            townWarning = (ImageView) findViewById(R.id.imageView14);
            townWarning.setVisibility(View.INVISIBLE);
            townWarningText = (TextView) findViewById(R.id.textView12);
            townWarningText.setVisibility(View.INVISIBLE);
            return true;
        } else {
            townWarning = (ImageView) findViewById(R.id.imageView14);
            townWarning.setVisibility(View.VISIBLE);
            townWarningText = (TextView) findViewById(R.id.textView12);
            townWarningText.setVisibility(View.VISIBLE);
            return false;
        }
    }
    public void createAccount(View view) {
        name = (EditText) findViewById(R.id.editTextTextPersonName2);
        lastName = (EditText) findViewById(R.id.editTextTextPersonName);
        DOB = (EditText) findViewById(R.id.editTextDate);
        streetName = (EditText) findViewById(R.id.editTextTextPostalAddress);
        postalCode = (EditText) findViewById(R.id.editTextTextPersonName3);
        town = (EditText) findViewById(R.id.editTextTextPersonName4);

        String nameString = name.getText().toString();
        String lastNameString = lastName.getText().toString();
        String DOBString = DOB.getText().toString();
        String streetNameString = streetName.getText().toString();
        String postalCodeString = postalCode.getText().toString();
        String townString = town.getText().toString();

        if (validateName(nameString) && validateLastName(lastNameString) && validateStreetName(streetNameString) && validateDOB(DOBString) && validatePostalCode(postalCodeString) && validateTown(townString)) {
            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
        }
    }
}