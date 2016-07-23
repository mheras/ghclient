package com.ghclient.app.di.user.events;

import com.ghclient.app.di.AppComponent;
import com.ghclient.app.di.base.IControllerComponent;
import com.ghclient.app.di.base.scope.ControllerScope;
import com.ghclient.app.presentation.user.events.EventsController;

import dagger.Component;

@ControllerScope
@Component(dependencies = AppComponent.class, modules = EventsModule.class)
public interface EventsComponent extends IControllerComponent<EventsController> {

}
