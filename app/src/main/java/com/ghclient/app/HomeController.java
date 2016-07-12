package com.ghclient.app;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;

import butterknife.BindView;

public class HomeController extends ContentController {

    @BindView(R.id.controller_home_repository_home_button)
    Button repositoryHomeButton;

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_home, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        repositoryHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRouter().pushController(RouterTransaction.with(new RepositoryHomeController()).pushChangeHandler(new HorizontalChangeHandler()).popChangeHandler(new HorizontalChangeHandler()));
                getMainController().setDrawerEnabled(false);
            }
        });
    }

    @Override
    protected void onAttach(@NonNull View view) {
        super.onAttach(view);
        getMainController().setDrawerEnabled(true);
    }
}
