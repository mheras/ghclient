package com.ghclient.app.presentation.base;

import rx.Observable;
import rx.subjects.BehaviorSubject;

public abstract class BasePresenter<ViewType extends IView> implements IPresenter<ViewType> {

    private BehaviorSubject<PresenterLifecycleEvent> lifecycleObservable = BehaviorSubject.create(PresenterLifecycleEvent.CREATE);
    private ViewType view;

    @Override
    public void onAttachView(ViewType view) {
        this.view = view;
        lifecycleObservable.onNext(PresenterLifecycleEvent.ATTACH);
    }

    @Override
    public void onDetachView() {
        lifecycleObservable.onNext(PresenterLifecycleEvent.DETACH);
        this.view = null;
    }

    protected ViewType getView() {
        return view;
    }

    @Override
    public void onDestroy() {
        lifecycleObservable.onNext(PresenterLifecycleEvent.DESTROY);
    }

    protected Observable<PresenterLifecycleEvent> getLifecycleObservable() {
        return lifecycleObservable;
    }
}
