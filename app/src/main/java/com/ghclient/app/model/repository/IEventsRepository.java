package com.ghclient.app.model.repository;

import com.ghclient.app.model.entity.Event;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface IEventsRepository {

    @GET("users/{username}/received_events")
    Observable<List<Event>> createReceivedEventsObservable(@Path("username") String username, @Query("page") int page);

}
