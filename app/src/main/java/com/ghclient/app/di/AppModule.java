package com.ghclient.app.di;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ghclient.app.App;
import com.ghclient.app.di.base.scope.AppScope;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @AppScope
    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @AppScope
    @Provides
    OkHttpClient provideHttpClient() {
        return new OkHttpClient.Builder().addInterceptor(new StethoInterceptor()).build();
    }

    @AppScope
    @Provides
    Retrofit provideRetrofit(OkHttpClient httpClient, Gson gson) {
        return new Retrofit.Builder().baseUrl("https://api.github.com/").client(httpClient).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    @AppScope
    @Provides
    App provideApp() {
        return app;
    }

    @AppScope
    @Provides
    Context provideAppContext() {
        return app.getApplicationContext();
    }

}
