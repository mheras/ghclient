package com.ghclient.app;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.ghclient.app.di.AppComponent;
import com.ghclient.app.di.AppModule;
import com.ghclient.app.di.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;

public class App extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // TODO: Move debugging tools to another flavour.
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }
        Stetho.initializeWithDefaults(this);
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

}
