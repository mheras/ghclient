package com.ghclient.app.di.user.events;

import com.ghclient.app.di.IAppComponent;
import com.ghclient.app.di.base.IControllerComponent;
import com.ghclient.app.di.base.scope.ControllerScope;
import com.ghclient.app.ui.controller.user.events.EventsController;

import dagger.Component;

@ControllerScope
@Component(dependencies = IAppComponent.class, modules = EventsModule.class)
public interface IEventsComponent extends IControllerComponent<EventsController> {

}
