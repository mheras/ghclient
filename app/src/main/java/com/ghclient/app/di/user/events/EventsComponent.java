package com.ghclient.app.di.user.events;

import com.ghclient.app.di.AppComponent;
import com.ghclient.app.di.base.IPresenterComponent;
import com.ghclient.app.di.base.scope.PresenterScope;
import com.ghclient.app.presentation.presenter.user.events.EventsPresenter;

import dagger.Component;

@PresenterScope
@Component(dependencies = AppComponent.class, modules = EventsModule.class)
public interface EventsComponent extends IPresenterComponent<EventsPresenter> {

}
