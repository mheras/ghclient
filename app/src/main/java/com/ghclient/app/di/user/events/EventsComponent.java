package com.ghclient.app.di.user.events;

import com.ghclient.app.di.AppComponent;
import com.ghclient.app.di.base.BaseControllerComponent;
import com.ghclient.app.presentation.user.events.EventsController;

import dagger.Component;

@Component(dependencies = AppComponent.class, modules = EventsModule.class)
public interface EventsComponent extends BaseControllerComponent<EventsController> {

}
