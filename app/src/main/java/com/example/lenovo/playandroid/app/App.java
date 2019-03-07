package com.example.lenovo.playandroid.app;

import android.app.Application;

/**
 * Created by lenovo on 2019/2/28.
 */

public class App extends Application {
    public static App sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
    }

    public static App getApplication() {
        return sApp;
    }
}
