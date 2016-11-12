package com.ghclient.app.di.repository.issues;

import com.ghclient.app.di.AppComponent;
import com.ghclient.app.di.base.IPresenterComponent;
import com.ghclient.app.di.base.scope.PresenterScope;
import com.ghclient.app.presentation.presenter.repository.issues.IssueDetailsPresenter;

import dagger.Component;

@PresenterScope
@Component(dependencies = AppComponent.class, modules = IssueDetailsModule.class)
public interface IssueDetailsComponent extends IPresenterComponent<IssueDetailsPresenter> {
}
