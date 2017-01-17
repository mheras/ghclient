package com.ghclient.app.ui.controller.repository.issues;

import com.ghclient.app.App;
import com.ghclient.app.R;
import com.ghclient.app.di.repository.issues.DaggerIIssueDetailsComponent;
import com.ghclient.app.di.repository.issues.IIssueDetailsComponent;
import com.ghclient.app.presentation.presenter.repository.issues.IIssueDetailsPresenter;
import com.ghclient.app.presentation.view.repository.issues.IIssueDetailsView;
import com.ghclient.app.ui.controller.common.base.BaseController;

public class IssueDetailsController extends BaseController<IIssueDetailsPresenter, IIssueDetailsView, IIssueDetailsComponent> implements IIssueDetailsView {

    @Override
    protected Class<IIssueDetailsView> getViewClass() {
        return IIssueDetailsView.class;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.controller_issue_details;
    }

    @Override
    protected IIssueDetailsComponent createControllerComponent(IIssueDetailsView view) {
        return DaggerIIssueDetailsComponent.builder().iAppComponent(App.getAppComponent()).build();
    }
}

