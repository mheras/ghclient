package com.ghclient.app.presentation.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.rxlifecycle.RxController;
import com.ghclient.app.App;
import com.ghclient.app.di.AppComponent;
import com.ghclient.app.di.base.IControllerComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseController<PresenterType extends IPresenter<ViewType>, ViewType extends IView, ControllerType extends BaseController<PresenterType, ViewType, ControllerType>> extends RxController implements IView {

    @Inject
    PresenterType presenter;
    private IControllerComponent<ControllerType> controllerComponent;
    private Unbinder unbinder;

    protected BaseController() {
        this(null);
    }

    protected BaseController(Bundle args) {
        super(args);
        controllerComponent = createControllerComponent(App.getAppComponent());
        if (controllerComponent != null) {
            // noinspection unchecked
            controllerComponent.inject((ControllerType) this);
        }
    }

    protected IControllerComponent<ControllerType> createControllerComponent(AppComponent appComponent) {
        return null;
    }

    protected abstract android.view.View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container);

    @NonNull
    @Override
    protected android.view.View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        android.view.View view = inflateView(inflater, container);
        unbinder = ButterKnife.bind(this, view);
        onViewBound(view);
        return view;
    }

    protected void onViewBound(@NonNull android.view.View view) {
        // Override if needed.
    }

    @Override
    protected void onDestroyView(android.view.View view) {
        super.onDestroyView(view);
        unbinder.unbind();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getRefWatcher().watch(this);
    }

    protected AppCompatActivity getAppCompatActivity() {
        return (AppCompatActivity) getActivity();
    }

    protected PresenterType getPresenter() {
        return presenter;
    }
}
