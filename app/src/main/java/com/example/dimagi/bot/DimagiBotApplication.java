package com.example.dimagi.bot;

import android.app.Application;

import com.example.dimagi.bot.rest.VolleyQueueManager;

public class DimagiBotApplication extends Application {

    private static DimagiBotApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        VolleyQueueManager.getInstance(this);
    }

    public static DimagiBotApplication getmInstance() {
        return mInstance;
    }
}
