package com.ghclient.app.presentation.presenter.repository.issues;

import com.ghclient.app.presentation.presenter.common.base.BasePresenter;
import com.ghclient.app.presentation.view.repository.issues.IIssueDetailsView;

import javax.inject.Inject;

public class IssueDetailsPresenter extends BasePresenter<IIssueDetailsView> implements IIssueDetailsPresenter {

    @Inject
    public IssueDetailsPresenter(IIssueDetailsView view) {
        super(view);
    }
}
