package com.ghclient.app.di.repository.issues;

import com.ghclient.app.di.base.scope.ControllerScope;
import com.ghclient.app.presentation.repository.issues.IIssueDetailsPresenter;
import com.ghclient.app.presentation.repository.issues.IIssueDetailsView;
import com.ghclient.app.presentation.repository.issues.IssueDetailsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class IssueDetailsModule {

    private IIssueDetailsView issueDetailsView;

    public IssueDetailsModule(IIssueDetailsView issueDetailsView) {
        this.issueDetailsView = issueDetailsView;
    }

    @ControllerScope
    @Provides
    IIssueDetailsView provideIssueDetailsView() {
        return issueDetailsView;
    }

    @Provides
    @ControllerScope
    IIssueDetailsPresenter provideIssueDetailsPresenter(IIssueDetailsView issueDetailsView) {
        return new IssueDetailsPresenter(issueDetailsView);
    }

}
