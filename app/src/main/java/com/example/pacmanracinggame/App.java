package com.example.pacmanracinggame;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GameServices.initHelper(this);
        MySp.initHelper(this);

    }
}