package com.ghclient.app.di.user.events;

import com.ghclient.app.di.base.scope.ControllerScope;
import com.ghclient.app.model.interactor.EventsInteractor;
import com.ghclient.app.model.interactor.IEventsInteractor;
import com.ghclient.app.model.repository.IEventsRepository;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class EventsModule {

    // TODO: Replace with @Binds.
    @Provides
    @ControllerScope
    IEventsInteractor provideEventsInteractor(EventsInteractor eventsInteractor) {
        return eventsInteractor;
    }

    @ControllerScope
    @Provides
    IEventsRepository provideEventsRepository(Retrofit retrofit) {
        return retrofit.create(IEventsRepository.class);
    }

}
