package com.example.hermes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

public class LoginScreenCz extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private TextView loginFailWarning;

    public static SharedPreferences sp = MainActivity.sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen_cz);
    }

    public void createAccount(View view){
        Intent intent = new Intent(this, RegisterAccountCz.class);
        startActivity(intent);
    }

    public void login(View view){
        Realm.init(this);
        DatabaseManager manager = DatabaseManager.getDatabaseManager();
        App app = manager.getApp();

        email = (EditText) findViewById(R.id.loginEmail);
        password = (EditText) findViewById(R.id.loginPassword);
        Intent intent = new Intent(this, HomeScreenCz.class);
        Credentials credentials = Credentials.emailPassword(email.getText().toString(), password.getText().toString());
        app.loginAsync(credentials, new App.Callback<User>() {
            @Override
            public void onResult(App.Result<User> result) {
                if(result.isSuccess()){
                    sp.edit().putBoolean("logged",true).apply();
                    sp.edit().putString("email", email.getText().toString()).apply();
                    sp.edit().putString("password",password.getText().toString()).apply();
                    startActivity(intent);
                } else {
                    loginFailWarning = (TextView) findViewById(R.id.textView17);
                    loginFailWarning.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void switchLanguage(View view){
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
    }
}