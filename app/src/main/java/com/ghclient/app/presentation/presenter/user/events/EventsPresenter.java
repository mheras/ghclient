package com.ghclient.app.presentation.presenter.user.events;

import com.ghclient.app.model.entity.Event;
import com.ghclient.app.model.repository.IEventsRepository;
import com.ghclient.app.presentation.presenter.common.list.ListPresenter;
import com.ghclient.app.presentation.view.user.events.IEventsView;

import javax.inject.Inject;

public class EventsPresenter extends ListPresenter<IEventsView, Event> implements IEventsPresenter {

    private IEventsRepository eventsRepository;

    @Inject
    public EventsPresenter(IEventsView view, IEventsRepository eventsRepository) {
        super(view);
        this.eventsRepository = eventsRepository;
    }
}
