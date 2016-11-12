package com.ghclient.app.presentation.presenter.user.events;

import com.arellomobile.mvp.InjectViewState;
import com.ghclient.app.App;
import com.ghclient.app.di.user.events.DaggerEventsComponent;
import com.ghclient.app.di.user.events.EventsComponent;
import com.ghclient.app.di.user.events.EventsModule;
import com.ghclient.app.model.entity.Event;
import com.ghclient.app.model.repository.IEventsRepository;
import com.ghclient.app.presentation.presenter.common.list.ListPresenter;
import com.ghclient.app.presentation.view.user.events.IEventsView;

import javax.inject.Inject;

@InjectViewState
public class EventsPresenter extends ListPresenter<IEventsView, Event, EventsComponent> implements IEventsPresenter {

    @Inject
    IEventsRepository eventsApi;

    @Override
    protected EventsComponent createPresenterComponent() {
        return DaggerEventsComponent.builder().appComponent(App.getAppComponent()).eventsModule(new EventsModule()).build();
    }
}
