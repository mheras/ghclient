package com.ghclient.app.di.main;

import com.ghclient.app.di.base.scope.ControllerScope;
import com.ghclient.app.presentation.main.IMainPresenter;
import com.ghclient.app.presentation.main.IMainView;
import com.ghclient.app.presentation.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private IMainView mainView;

    public MainModule(IMainView mainView) {
        this.mainView = mainView;
    }

    @ControllerScope
    @Provides
    IMainView provideMainView() {
        return mainView;
    }

    @ControllerScope
    @Provides
    IMainPresenter provideMainPresenter(IMainView mainView) {
        return new MainPresenter(mainView);
    }

}
