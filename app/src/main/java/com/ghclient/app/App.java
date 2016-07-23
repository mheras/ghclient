package com.ghclient.app;

import android.app.Application;

import com.ghclient.app.di.AppComponent;
import com.ghclient.app.di.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class App extends Application {

    private static AppComponent appComponent;
    private static RefWatcher refWatcher;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static RefWatcher getRefWatcher() {
        return refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
        appComponent = DaggerAppComponent.create();
    }

}
