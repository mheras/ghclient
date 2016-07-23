package com.ghclient.app.presentation.user.events;

import android.os.Bundle;

import com.ghclient.app.di.AppComponent;
import com.ghclient.app.di.base.IControllerComponent;
import com.ghclient.app.di.user.events.DaggerEventsComponent;
import com.ghclient.app.di.user.events.EventsModule;
import com.ghclient.app.presentation.list.ListController;
import com.ghclient.app.util.BundleBuilder;

public class EventsController extends ListController<EventsPresenter, IEventsView, EventsController> implements IEventsView {

    private final static String USERNAME_KEY = "username";

    public EventsController(String username) {
        this(new BundleBuilder(new Bundle()).putString(USERNAME_KEY, username).build());
    }

    public EventsController(Bundle args) {
        super(args);
        String username = args.getString(USERNAME_KEY);
    }

    @Override
    protected IControllerComponent<EventsController> createControllerComponent(AppComponent appComponent) {
        return DaggerEventsComponent.builder().appComponent(appComponent).eventsModule(new EventsModule()).build();
    }
}
