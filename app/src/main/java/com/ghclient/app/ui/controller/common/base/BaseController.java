package com.ghclient.app.ui.controller.common.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.ghclient.app.di.base.IControllerComponent;
import com.ghclient.app.presentation.presenter.common.base.BasePresenter;
import com.ghclient.app.presentation.presenter.common.base.IPresenter;
import com.ghclient.app.presentation.view.common.base.IView;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import icepick.Icepick;
import icepick.State;

public abstract class BaseController<PresenterType extends IPresenter, ViewType extends IView, ControllerComponentType extends IControllerComponent> extends Controller implements IView {

    @Inject
    PresenterType presenter;
    @State
    ArrayList<ViewCommand> pendingViewCommands = new ArrayList<>();

    private ControllerComponentType controllerComponent;
    private Unbinder unbinder;

    public BaseController() {
        this(null);
    }

    public BaseController(@Nullable Bundle args) {
        super(args);

        // noinspection unchecked
        ViewType viewProxy = (ViewType) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{getViewClass()}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                // TODO: Validate return type is void.
                // TODO: Validate all args are Serializable.
                // TODO: Validate that this is being executed on the main thread.

                if (isAttached() && pendingViewCommands.isEmpty()) {
                    method.invoke(BaseController.this, args);
                } else {
                    pendingViewCommands.add(new ViewCommand(method, args));
                    Log.e("MATO", "Saving pending view command");
                }

                return null;
            }
        });

        controllerComponent = createControllerComponent(viewProxy);
        if (controllerComponent != null) {
            // noinspection unchecked
            controllerComponent.inject(this);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle state) {
        super.onSaveInstanceState(state);
        Icepick.saveInstanceState(this, state);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle state) {
        super.onRestoreInstanceState(state);
        Icepick.restoreInstanceState(this, state);
    }

    protected abstract Class<ViewType> getViewClass();

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

    protected abstract ControllerComponentType createControllerComponent(ViewType view);

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
