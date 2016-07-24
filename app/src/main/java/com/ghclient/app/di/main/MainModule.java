package com.ghclient.app.di.main;

import com.ghclient.app.di.base.scope.ControllerScope;
import com.ghclient.app.presentation.main.IMainPresenter;
import com.ghclient.app.presentation.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @ControllerScope
    @Provides
    IMainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

}
