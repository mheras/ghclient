package com.ghclient.app.di.repository.issues;

import com.ghclient.app.di.AppComponent;
import com.ghclient.app.di.base.BaseControllerComponent;
import com.ghclient.app.presentation.repository.issues.IssueDetailsController;

import dagger.Component;

@Component(dependencies = AppComponent.class, modules = IssueDetailsModule.class)
public interface IssueDetailsComponent extends BaseControllerComponent<IssueDetailsController> {
}
