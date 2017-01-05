package com.ghclient.app.di;

import com.ghclient.app.App;
import com.ghclient.app.di.base.IComponent;
import com.ghclient.app.di.base.scope.AppScope;

import dagger.Component;
import retrofit2.Retrofit;

@AppScope
@Component(modules = AppModule.class)
public interface IAppComponent extends IComponent<App> {

    App app();

    Retrofit retrofit();

}
