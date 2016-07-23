package com.ghclient.app.model.interactor;

import com.ghclient.app.model.entity.Event;
import com.ghclient.app.model.repository.IEventsRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class EventsInteractor implements IEventsInteractor {

    private IEventsRepository eventsRepository;

    @Inject
    public EventsInteractor(IEventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public Observable<List<Event>> getReceivedEvents(String username, int page) {
        return eventsRepository.getReceivedEvents(username, page);
    }
}
