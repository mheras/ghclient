package com.ghclient.app.presentation.presenter.common.base;

import com.ghclient.app.presentation.view.common.base.IView;

public abstract class BasePresenter<ViewType extends IView> implements IPresenter {

    private ViewType view;

    public BasePresenter(ViewType view) {
        this.view = view;
    }

    public void onDestroy() {
        view = null;
    }

    protected final ViewType getView() {
        return view;
    }

}
