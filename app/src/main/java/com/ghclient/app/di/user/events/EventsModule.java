package com.ghclient.app.di.user.events;

import com.ghclient.app.di.base.scope.PresenterScope;
import com.ghclient.app.model.repository.IEventsRepository;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class EventsModule {

    // TODO: Replace with @Binds (in every module).

    @PresenterScope
    @Provides
    IEventsRepository provideEventsApi(Retrofit retrofit) {
        return retrofit.create(IEventsRepository.class);
    }

}
