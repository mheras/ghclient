package com.ghclient.app.di.repository.issues;

import com.ghclient.app.di.IAppComponent;
import com.ghclient.app.di.base.IControllerComponent;
import com.ghclient.app.di.base.scope.ControllerScope;
import com.ghclient.app.ui.controller.repository.issues.IssueDetailsController;

import dagger.Component;

@ControllerScope
@Component(dependencies = IAppComponent.class, modules = IssueDetailsModule.class)
public interface IIssueDetailsComponent extends IControllerComponent<IssueDetailsController> {
}
