package com.ghclient.app.presentation.presenter.common.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;

import com.ghclient.app.presentation.view.common.base.IView;

import org.reactivestreams.Subscription;

import java.util.HashMap;

import icepick.Icepick;

public abstract class BasePresenter<ViewType extends IView> implements IPresenter {

    private ViewType view;

    // TODO: Use CompositeDisposable
    private final HashMap<Integer, Subscription> restartableSubscriptions = new HashMap<>();

    public BasePresenter(ViewType view) {
        this.view = view;
    }

    protected final ViewType getView() {
        return view;
    }

    public final void onSave(Bundle state) {
        Icepick.saveInstanceState(this, state);
        // TODO: Unsubscribe from all the subscriptions
        // TODO: Save current pending subscriptions
    }

    public final void onRestore(Bundle state) {
        Icepick.restoreInstanceState(this, state);
    }

    @CallSuper
    public void onDestroy() {
        view = null;
    }

}
