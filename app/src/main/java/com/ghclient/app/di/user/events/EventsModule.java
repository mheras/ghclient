package com.ghclient.app.di.user.events;

import com.ghclient.app.di.base.scope.ControllerScope;
import com.ghclient.app.model.repository.IEventsRepository;
import com.ghclient.app.presentation.presenter.user.events.EventsPresenter;
import com.ghclient.app.presentation.presenter.user.events.IEventsPresenter;
import com.ghclient.app.presentation.view.user.events.IEventsView;
import com.ghclient.app.ui.controller.user.events.EventsController;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class EventsModule {

    private EventsController controller;

    public EventsModule(EventsController controller) {
        this.controller = controller;
    }

    @Provides
    @ControllerScope
    IEventsView provideView() {
        return controller;
    }

    @Provides
    @ControllerScope
    IEventsPresenter providePresenter(EventsPresenter presenter) {
        return presenter;
    }

    @Provides
    @ControllerScope
    IEventsRepository provideEventsApi(Retrofit retrofit) {
        return retrofit.create(IEventsRepository.class);
    }

}
