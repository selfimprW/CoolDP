package com.yaerin.cooldp;

import android.app.Application;

/**
 * Created by yaerin on 11/10/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.initialize(this);
    }
}
