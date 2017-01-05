package com.ghclient.app.di.user.home;

import com.ghclient.app.di.IAppComponent;
import com.ghclient.app.di.base.IControllerComponent;
import com.ghclient.app.di.base.scope.ControllerScope;
import com.ghclient.app.ui.controller.user.home.HomeController;

import dagger.Component;

@ControllerScope
@Component(dependencies = IAppComponent.class, modules = HomeModule.class)
public interface IHomeComponent extends IControllerComponent<HomeController> {
}
