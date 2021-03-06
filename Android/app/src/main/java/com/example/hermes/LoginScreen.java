package com.example.hermes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hermes.databinding.ActivityLoginScreenBinding;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;


public class LoginScreen extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityLoginScreenBinding binding;

    private EditText email;
    private EditText password;
    private TextView loginFailWarning;

    public static SharedPreferences sp = MainActivity.sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Realm.init(this);
        super.onCreate(savedInstanceState);

        binding = ActivityLoginScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_login_screen);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void createAccount(View view){
        Intent intent = new Intent(this, RegisterAccount.class);
        startActivity(intent);
    }

    public void login(View view){
        Realm.init(this);
        DatabaseManager manager = DatabaseManager.getDatabaseManager();
        App app = manager.getApp();

        email = (EditText) findViewById(R.id.loginEmail);
        password = (EditText) findViewById(R.id.loginPassword);
        Intent intent = new Intent(this, HomeScreen.class);
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
        Intent intent = new Intent(this, LoginScreenCz.class);
        startActivity(intent);
    }
}