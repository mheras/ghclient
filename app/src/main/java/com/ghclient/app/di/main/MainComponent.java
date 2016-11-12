package com.ghclient.app.di.main;

import com.ghclient.app.di.AppComponent;
import com.ghclient.app.di.base.IPresenterComponent;
import com.ghclient.app.di.base.scope.PresenterScope;
import com.ghclient.app.presentation.presenter.main.MainPresenter;

import dagger.Component;

@PresenterScope
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent extends IPresenterComponent<MainPresenter> {
}
