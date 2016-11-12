package com.ghclient.app.presentation.presenter.common.base;

import com.arellomobile.mvp.MvpPresenter;
import com.ghclient.app.di.base.IPresenterComponent;
import com.ghclient.app.presentation.view.common.base.IView;

public abstract class BasePresenter<ViewType extends IView, PresenterComponentType extends IPresenterComponent> extends MvpPresenter<ViewType> implements IPresenter<ViewType> {

    private PresenterComponentType presenterComponent;

    public BasePresenter() {
        super();
        presenterComponent = createPresenterComponent();
        if (presenterComponent != null) {
            // noinspection unchecked
            presenterComponent.inject(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterComponent = null;
    }

    protected abstract PresenterComponentType createPresenterComponent();
}
