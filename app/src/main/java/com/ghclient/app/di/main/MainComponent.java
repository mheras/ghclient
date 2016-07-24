package com.ghclient.app.di.main;

import com.ghclient.app.di.AppComponent;
import com.ghclient.app.di.base.IControllerComponent;
import com.ghclient.app.di.base.scope.ControllerScope;
import com.ghclient.app.presentation.main.MainController;

import dagger.Component;

@ControllerScope
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent extends IControllerComponent<MainController> {
}
