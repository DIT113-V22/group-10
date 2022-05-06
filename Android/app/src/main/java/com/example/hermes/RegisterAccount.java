package com.example.hermes;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hermes.databinding.ActivityRegisterAccountBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterAccount extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityRegisterAccountBinding binding;
    private EditText editTextTextEmailAddress3;
    private EditText editTextTextPassword2;
    private EditText editTextTextPassword3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

     /*   setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_register_account);
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
    public void accountDetails(View view){
        Intent intent = new Intent(this, CreateAccountDetails.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_register_account);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void createAccount(View view){
        editTextTextEmailAddress3 = (EditText) findViewById(R.id.editTextTextEmailAddress3);
        editTextTextPassword2 = (EditText) findViewById(R.id.editTextTextPassword2);
        editTextTextPassword3 = (EditText) findViewById(R.id.editTextTextPassword3);

        String email = editTextTextEmailAddress3.getText().toString();
        String password2 = editTextTextPassword2.getText().toString();
        String password3 = editTextTextPassword3.getText().toString();

        if(password2.equals(password3)){
            Account account = new Account(email, password2);
        }

    }
    public boolean validateEmail(String email){
        Pattern checkPattern = Pattern.compile("^[\\w-\\.+]*[\\w-\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
        Matcher match = checkPattern.matcher(email);
        return match.find();
    }
    public boolean validatePassword(String password){
        Pattern checkPattern = Pattern.compile("[^a-zA-Z0-9]"); //regex, checks everything that is not a special case character
        Pattern checkNumberPattern = Pattern.compile("[0-9]"); //check numbers
        Matcher match = checkPattern.matcher(password);
        Matcher matchNumber = checkNumberPattern.matcher(password);
        if(!password.isEmpty() && password.length() >= 8 && !password.toUpperCase().equals(password) && !password.toLowerCase().equals(password) && match.find() && matchNumber.find()){
            return true;
        }else{
            return false;
        }
    }
}