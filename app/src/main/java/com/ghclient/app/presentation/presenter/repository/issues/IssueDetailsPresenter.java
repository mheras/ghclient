package com.ghclient.app.presentation.presenter.repository.issues;

import com.arellomobile.mvp.InjectViewState;
import com.ghclient.app.App;
import com.ghclient.app.di.repository.issues.DaggerIssueDetailsComponent;
import com.ghclient.app.di.repository.issues.IssueDetailsComponent;
import com.ghclient.app.presentation.presenter.common.base.BasePresenter;
import com.ghclient.app.presentation.view.repository.issues.IIssueDetailsView;

@InjectViewState
public class IssueDetailsPresenter extends BasePresenter<IIssueDetailsView, IssueDetailsComponent> implements IIssueDetailsPresenter {

    @Override
    protected IssueDetailsComponent createPresenterComponent() {
        return DaggerIssueDetailsComponent.builder().appComponent(App.getAppComponent()).build();
    }
}
