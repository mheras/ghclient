package com.ghclient.app.presentation.repository.issues;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ghclient.app.R;
import com.ghclient.app.di.AppComponent;
import com.ghclient.app.di.base.BaseControllerComponent;
import com.ghclient.app.di.repository.issues.DaggerIssueDetailsComponent;
import com.ghclient.app.presentation.base.BaseController;

public class IssueDetailsController extends BaseController<IssueDetailsPresenter, IssueDetailsView, IssueDetailsController> implements IssueDetailsView {

    @Override
    protected BaseControllerComponent<IssueDetailsController> createControllerComponent(AppComponent appComponent) {
        return DaggerIssueDetailsComponent.builder().appComponent(appComponent).build();
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_issue_details, container, false);
    }

}
