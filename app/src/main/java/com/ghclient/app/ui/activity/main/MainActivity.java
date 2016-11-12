package com.ghclient.app.ui.activity.main;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.ghclient.app.R;
import com.ghclient.app.presentation.presenter.main.IMainPresenter;
import com.ghclient.app.presentation.presenter.main.MainPresenter;
import com.ghclient.app.presentation.view.main.IMainView;
import com.ghclient.app.ui.activity.common.base.BaseActivity;

public class MainActivity extends BaseActivity<IMainPresenter> implements IMainView {

    @InjectPresenter
    MainPresenter presenter;

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected IMainPresenter getPresenter() {
        return presenter;
    }
}

