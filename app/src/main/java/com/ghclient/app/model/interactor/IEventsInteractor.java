package com.ghclient.app.model.interactor;

import com.ghclient.app.model.entity.Event;

import java.util.List;

import rx.Observable;

public interface IEventsInteractor {
    Observable<List<Event>> getReceivedEvents(String username, int page);
}
