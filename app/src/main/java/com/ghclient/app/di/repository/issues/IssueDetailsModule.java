package com.ghclient.app.di.repository.issues;

import com.ghclient.app.di.base.scope.ControllerScope;
import com.ghclient.app.presentation.presenter.repository.issues.IIssueDetailsPresenter;
import com.ghclient.app.presentation.presenter.repository.issues.IssueDetailsPresenter;
import com.ghclient.app.presentation.view.repository.issues.IIssueDetailsView;
import com.ghclient.app.ui.controller.repository.issues.IssueDetailsController;

import dagger.Module;
import dagger.Provides;

@Module
public class IssueDetailsModule {

    private IssueDetailsController controller;

    public IssueDetailsModule(IssueDetailsController controller) {
        this.controller = controller;
    }

    @Provides
    @ControllerScope
    IIssueDetailsView provideView() {
        return controller;
    }

    @Provides
    @ControllerScope
    IIssueDetailsPresenter providePresenter(IssueDetailsPresenter presenter) {
        return presenter;
    }
}
