package com.ghclient.app.ui.controller.common.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.ghclient.app.di.base.IControllerComponent;
import com.ghclient.app.presentation.presenter.common.base.BasePresenter;
import com.ghclient.app.presentation.presenter.common.base.IPresenter;
import com.ghclient.app.presentation.view.common.base.IView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseController<PresenterType extends IPresenter, ViewType extends IView, ControllerComponentType extends IControllerComponent> extends Controller implements IView {

    private ControllerComponentType controllerComponent;
    private Unbinder unbinder;

    @Inject
    PresenterType presenter;

    public BaseController() {
        this(null);
    }

    public BaseController(@Nullable Bundle args) {
        super(args);
        controllerComponent = createControllerComponent();
        if (controllerComponent != null) {
            // noinspection unchecked
            controllerComponent.inject(this);
        }
    }

    protected PresenterType getPresenter() {
        return presenter;
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        onViewBound(view);
        return view;
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract ControllerComponentType createControllerComponent();

    protected void onViewBound(@NonNull View view) {}

    @Override
    protected void onDestroy() {
        ((BasePresenter) presenter).onDestroy();
        presenter = null;
        controllerComponent = null;
        super.onDestroy();
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        unbinder.unbind();
        unbinder = null;
        super.onDestroyView(view);
    }

    @Override
    public void showError(Throwable throwable) {

        View view = getView();
        assert view != null;

        // TODO: strings.xml
        Snackbar.make(view, "An error has occurred", Snackbar.LENGTH_SHORT).show();
    }
}
