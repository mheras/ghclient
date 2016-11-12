package com.ghclient.app.presentation.presenter.main;

import com.arellomobile.mvp.InjectViewState;
import com.ghclient.app.App;
import com.ghclient.app.di.main.DaggerMainComponent;
import com.ghclient.app.di.main.MainComponent;
import com.ghclient.app.presentation.presenter.common.base.BasePresenter;
import com.ghclient.app.presentation.view.main.IMainView;

@InjectViewState
public class MainPresenter extends BasePresenter<IMainView, MainComponent> implements IMainPresenter {

    @Override
    protected MainComponent createPresenterComponent() {
        return DaggerMainComponent.builder().appComponent(App.getAppComponent()).build();
    }
}
