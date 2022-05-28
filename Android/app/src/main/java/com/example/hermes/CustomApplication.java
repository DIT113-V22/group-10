package com.example.hermes;

import android.app.Application;

public class CustomApplication extends Application {
    private static CustomApplication INSTANCE;

    public static CustomApplication get() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (INSTANCE == null) {
            INSTANCE = this;
        }
    }
}
