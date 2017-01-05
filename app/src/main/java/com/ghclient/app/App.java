package com.ghclient.app;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.ghclient.app.di.DaggerIAppComponent;
import com.ghclient.app.di.IAppComponent;
import com.ghclient.app.di.AppModule;
import com.squareup.leakcanary.LeakCanary;

public class App extends Application {

    private static IAppComponent appComponent;

    public static IAppComponent getAppComponent() {
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
        appComponent = DaggerIAppComponent.builder().appModule(new AppModule(this)).build();
    }

}
