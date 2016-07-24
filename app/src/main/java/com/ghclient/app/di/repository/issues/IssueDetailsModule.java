package com.ghclient.app.di.repository.issues;

import com.ghclient.app.di.base.scope.ControllerScope;
import com.ghclient.app.presentation.repository.issues.IIssueDetailsPresenter;
import com.ghclient.app.presentation.repository.issues.IssueDetailsPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class IssueDetailsModule {

    @Provides
    @ControllerScope
    IIssueDetailsPresenter provideIssueDetailsPresenter() {
        return new IssueDetailsPresenter();
    }

}
