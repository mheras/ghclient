package com.ghclient.app.di.user.home;

import com.ghclient.app.di.base.scope.ControllerScope;
import com.ghclient.app.presentation.presenter.user.home.HomePresenter;
import com.ghclient.app.presentation.presenter.user.home.IHomePresenter;
import com.ghclient.app.presentation.view.user.home.IHomeView;
import com.ghclient.app.ui.controller.user.home.HomeController;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    private IHomeView view;

    public HomeModule(IHomeView view) {
        this.view = view;
    }

    @Provides
    @ControllerScope
    IHomeView provideView() {
        return view;
    }

    @Provides
    @ControllerScope
    IHomePresenter providePresenter(HomePresenter presenter) {
        return presenter;
    }
}
