package com.example.hermes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.realm.mongodb.App;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

public class RegisterAccountCz extends AppCompatActivity {

    private EditText editTextTextEmailAddress3;
    private EditText editTextTextPassword2;
    private EditText editTextTextPassword3;
    private ImageView emailWarning;
    private ImageView passwordWarning;
    private ImageView passwordMatchWarning;
    private TextView emailWarningText;
    private TextView passwordWarningText;
    private TextView passwordMatchWarningText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account_cz);
    }

    public void createAccount(View view){
        editTextTextEmailAddress3 = (EditText) findViewById(R.id.editTextTextEmailAddress3);
        editTextTextPassword2 = (EditText) findViewById(R.id.editTextTextPassword2);
        editTextTextPassword3 = (EditText) findViewById(R.id.editTextTextPassword3);

        String email = editTextTextEmailAddress3.getText().toString();
        String password2 = editTextTextPassword2.getText().toString();
        String password3 = editTextTextPassword3.getText().toString();

        if(passwordMatch(password2, password3) && validateEmail(email) && validatePassword(password2)){

            DatabaseManager manager = DatabaseManager.getDatabaseManager();
            App app = manager.getApp();

            app.getEmailPassword().registerUserAsync(email, password2, it->{
                if(it.isSuccess()){
                    Intent intent = new Intent(this, CreateAccountDetailsCz.class);
                    Credentials credentials = Credentials.emailPassword(email, password2);
                    app.loginAsync(credentials, new App.Callback<User>() {
                        @Override
                        public void onResult(App.Result<User> result) {
                            if(result.isSuccess()){
                                startActivity(intent);
                            } else {
                            }
                        }
                    });
                } else {
                    Log.v("Registration", "failed" + it.getError().toString());
                }
            });
        }
    }

    public boolean passwordMatch(String password, String password2){
        if(password.equals(password2)){
            passwordMatchWarning = (ImageView) findViewById(R.id.imageView8);
            passwordMatchWarning.setVisibility(View.INVISIBLE);
            passwordMatchWarningText = (TextView) findViewById(R.id.textView5);
            passwordMatchWarningText.setVisibility(View.INVISIBLE);
            return true;
        }else{
            passwordMatchWarning = (ImageView) findViewById(R.id.imageView8);
            passwordMatchWarning.setVisibility(View.VISIBLE);
            passwordMatchWarningText = (TextView) findViewById(R.id.textView5);
            passwordMatchWarningText.setVisibility(View.VISIBLE);

            return false;
        }
    }

    public boolean validateEmail(String email){
        Pattern checkPattern = Pattern.compile("^[\\w-\\.+]*[\\w-\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
        Matcher match = checkPattern.matcher(email);
        if(match.find()){
            emailWarning = (ImageView) findViewById(R.id.imageView6);
            emailWarning.setVisibility(View.INVISIBLE);
            emailWarningText = (TextView) findViewById(R.id.textView2);
            emailWarningText.setVisibility(View.INVISIBLE);
            return true;
        }else{
            emailWarning = (ImageView) findViewById(R.id.imageView6);
            emailWarning.setVisibility(View.VISIBLE);
            emailWarningText = (TextView) findViewById(R.id.textView2);
            emailWarningText.setVisibility(View.VISIBLE);
            return false;
        }
    }

    public boolean validatePassword(String password){
        Pattern checkPattern = Pattern.compile("[^a-zA-Z0-9]"); //regex, checks everything that is not a special case character
        Pattern checkNumberPattern = Pattern.compile("[0-9]"); //check numbers
        Matcher match = checkPattern.matcher(password);
        Matcher matchNumber = checkNumberPattern.matcher(password);
        if(!password.isEmpty() && password.length() >= 8 && !password.toUpperCase().equals(password) && !password.toLowerCase().equals(password) && match.find() && matchNumber.find()){
            passwordWarning = (ImageView) findViewById(R.id.imageView9);
            passwordWarning.setVisibility(View.INVISIBLE);
            passwordWarningText = (TextView) findViewById(R.id.textView4);
            passwordWarningText.setVisibility(View.INVISIBLE);
            return true;
        }else{
            passwordWarning = (ImageView) findViewById(R.id.imageView9);
            passwordWarning.setVisibility(View.VISIBLE);
            passwordWarningText = (TextView) findViewById(R.id.textView4);
            passwordWarningText.setVisibility(View.VISIBLE);
            return false;
        }
    }

    public void goBack(View view){
        Intent intent = new Intent(this, LoginScreenCz.class);
        startActivity(intent);
    }
}