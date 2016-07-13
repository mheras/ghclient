package com.ghclient.app.presentation.user.events;

import android.os.Bundle;

import com.ghclient.app.presentation.list.ListController;
import com.ghclient.app.util.BundleBuilder;

public class EventsController extends ListController<EventsPresenter, IEventsView> implements IEventsView {

    private final static String USERNAME_KEY = "username";

    public EventsController(String username) {
        this(new BundleBuilder(new Bundle()).putString(USERNAME_KEY, username).build());
    }

    public EventsController(Bundle args) {
        super(args);
        String username = args.getString(USERNAME_KEY);
    }
}
