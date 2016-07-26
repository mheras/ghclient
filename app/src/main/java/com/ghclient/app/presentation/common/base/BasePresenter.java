package com.ghclient.app.presentation.common.base;

import rx.Observable;
import rx.subjects.BehaviorSubject;

public abstract class BasePresenter<ViewType extends IView> implements IPresenter<ViewType> {

    // TODO: What happens if the process is killed? We are not saving the Presenter state in disk as Controllers do.

    private BehaviorSubject<PresenterEvent> lifecycleSubject;
    private ViewType view;

    public BasePresenter(ViewType view) {
        this.view = view;
        lifecycleSubject = BehaviorSubject.create(PresenterEvent.CREATE);
    }

    public void onDestroy() {
        lifecycleSubject.onNext(PresenterEvent.DESTROY);
    }

    protected ViewType getView() {
        return view;
    }

    protected Observable<PresenterEvent> lifecycle() {
        return lifecycleSubject.asObservable();
    }
}
