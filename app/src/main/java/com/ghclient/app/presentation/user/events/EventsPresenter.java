package com.ghclient.app.presentation.user.events;

import android.util.Log;

import com.ghclient.app.model.entity.Event;
import com.ghclient.app.model.interactor.IEventsInteractor;
import com.ghclient.app.presentation.list.ListPresenter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class EventsPresenter extends ListPresenter<IEventsView> implements IEventsPresenter {

    private IEventsInteractor eventsInteractor;

    public EventsPresenter(IEventsInteractor eventsInteractor) {

        this.eventsInteractor = eventsInteractor;

        Log.e("MATO", "POR TIRAR REQUEST");
        eventsInteractor.getReceivedEvents("mheras", 1).delay(5, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Subscriber<List<Event>>() {

            @Override
            public void onCompleted() {
                Log.e("MATO", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("MATO", "onError " + e.toString());
            }

            @Override
            public void onNext(List<Event> event) {
                Log.e("MATO", "onNext");
            }
        });

    }

}
