package com.ghclient.app.di.repository.issues;

import com.ghclient.app.di.AppComponent;
import com.ghclient.app.di.base.IControllerComponent;
import com.ghclient.app.di.base.scope.ControllerScope;
import com.ghclient.app.presentation.repository.issues.IssueDetailsController;

import dagger.Component;

@ControllerScope
@Component(dependencies = AppComponent.class, modules = IssueDetailsModule.class)
public interface IssueDetailsComponent extends IControllerComponent<IssueDetailsController> {
}
