package com.ghclient.app.presentation.presenter.user.home;

import com.ghclient.app.presentation.presenter.common.base.BasePresenter;
import com.ghclient.app.presentation.view.user.home.IHomeView;

import javax.inject.Inject;

public class HomePresenter extends BasePresenter<IHomeView> implements IHomePresenter {

    @Inject
    public HomePresenter(IHomeView view) {
        super(view);
    }
}
