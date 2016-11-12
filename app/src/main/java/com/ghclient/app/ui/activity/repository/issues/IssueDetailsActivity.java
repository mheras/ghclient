package com.ghclient.app.ui.activity.repository.issues;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.ghclient.app.R;
import com.ghclient.app.presentation.presenter.repository.issues.IIssueDetailsPresenter;
import com.ghclient.app.presentation.presenter.repository.issues.IssueDetailsPresenter;
import com.ghclient.app.presentation.view.repository.issues.IIssueDetailsView;
import com.ghclient.app.ui.activity.common.base.BaseActivity;

public class IssueDetailsActivity extends BaseActivity<IIssueDetailsPresenter> implements IIssueDetailsView {

    @InjectPresenter
    IssueDetailsPresenter presenter;

    @Override
    protected IIssueDetailsPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.controller_issue_details;
    }
}
