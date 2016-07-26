package com.ghclient.app.presentation.user.events;

import com.ghclient.app.model.api.IEventsApi;
import com.ghclient.app.model.entity.Event;
import com.ghclient.app.presentation.common.list.PaginatedListPresenter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class EventsPresenter extends PaginatedListPresenter<IEventsView, Event> implements IEventsPresenter {

    private IEventsApi eventsApi;

    public EventsPresenter(IEventsView view, int perPage, IEventsApi eventsApi) {
        super(view, perPage);
        this.eventsApi = eventsApi;
    }

    @Override
    protected Observable<List<Event>> pageData(int page, int perPage) {

        //return eventsApi.receivedEvents("mheras", page);

        List<Event> events = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Event e = new Event();
            e.setType("Page: " + page + ", Item: " + i);
            events.add(e);
        }
        return Observable.just(events);
    }
}
