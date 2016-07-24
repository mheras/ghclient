package com.ghclient.app.presentation.repository.issues;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ghclient.app.R;
import com.ghclient.app.di.AppComponent;
import com.ghclient.app.di.base.IControllerComponent;
import com.ghclient.app.di.repository.issues.DaggerIssueDetailsComponent;
import com.ghclient.app.presentation.base.BaseController;

public class IssueDetailsController extends BaseController<IIssueDetailsPresenter, IIssueDetailsView, IssueDetailsController> implements IIssueDetailsView {

    @Override
    protected IControllerComponent<IssueDetailsController> createControllerComponent(AppComponent appComponent) {
        return DaggerIssueDetailsComponent.builder().appComponent(appComponent).build();
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_issue_details, container, false);
    }

}
