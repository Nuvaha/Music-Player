package com.example.musicplayer;

import android.app.Application;

import com.example.musicplayer.service.MP3Service;

public class App extends Application {

    private MP3Service service;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public MP3Service getService() {
        return service;
    }

    public void setService(MP3Service service) {
        this.service = service;
    }
}
