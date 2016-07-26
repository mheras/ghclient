package com.ghclient.app.di.user.events;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ghclient.app.di.base.scope.ControllerScope;
import com.ghclient.app.model.api.IEventsApi;
import com.ghclient.app.presentation.user.events.EventsPresenter;
import com.ghclient.app.presentation.user.events.IEventsPresenter;
import com.ghclient.app.presentation.user.events.IEventsView;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class EventsModule {

    // TODO: Replace with @Binds (in every module).

    private IEventsView eventsView;

    public EventsModule(IEventsView eventsView) {
        this.eventsView = eventsView;
    }

    @ControllerScope
    @Provides
    IEventsView provideEventsView() {
        return eventsView;
    }

    @ControllerScope
    @Provides
    IEventsApi provideEventsApi(Retrofit retrofit) {
        return retrofit.create(IEventsApi.class);
    }

    @ControllerScope
    @Provides
    IEventsPresenter provideEventsPresenter(IEventsView eventsView, IEventsApi eventsApi) {
        return new EventsPresenter(eventsView, 30, eventsApi);
    }

    @Provides
    RecyclerView.LayoutManager provideLayoutManager(@Named("ActivityContext") Context context) {
        return new LinearLayoutManager(context);
    }

}
