package com.ghclient.app.presentation.user.events;

import com.ghclient.app.model.entity.Event;
import com.ghclient.app.model.interactor.IEventsInteractor;
import com.ghclient.app.presentation.list.ListPresenter;

import java.util.List;

import rx.Observable;


public class EventsPresenter extends ListPresenter<IEventsView, Event> implements IEventsPresenter {

    private IEventsInteractor eventsInteractor;

    public EventsPresenter(IEventsInteractor eventsInteractor, int perPage) {

        super(perPage);
        this.eventsInteractor = eventsInteractor;
    }

    @Override
    protected Observable<List<Event>> createListDataObservable(int page, int perPage) {
        return eventsInteractor.createReceivedEventsObservable("mheras", page);
    }
}
