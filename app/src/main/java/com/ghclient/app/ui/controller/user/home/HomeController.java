package com.ghclient.app.ui.controller.user.home;

import com.ghclient.app.App;
import com.ghclient.app.R;
import com.ghclient.app.di.user.home.DaggerIHomeComponent;
import com.ghclient.app.di.user.home.HomeModule;
import com.ghclient.app.di.user.home.IHomeComponent;
import com.ghclient.app.presentation.presenter.user.home.IHomePresenter;
import com.ghclient.app.presentation.view.user.home.IHomeView;
import com.ghclient.app.ui.controller.common.base.BaseController;

public class HomeController extends BaseController<IHomePresenter, IHomeView, IHomeComponent> implements IHomeView {

    @Override
    protected int getLayoutResId() {
        return R.layout.controller_home;
    }

    @Override
    protected IHomeComponent createControllerComponent() {
        return DaggerIHomeComponent.builder().iAppComponent(App.getAppComponent()).homeModule(new HomeModule(this)).build();
    }

}
