package com.ghclient.app.ui.controller.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.ghclient.app.di.base.IControllerComponent;
import com.ghclient.app.presentation.presenter.common.base.BasePresenter;
import com.ghclient.app.presentation.presenter.common.base.IPresenter;
import com.ghclient.app.presentation.view.common.base.IView;
import com.ghclient.app.util.Assertion;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @NonNull
    @Override
    protected final View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {

        setupComponent();

        View view = inflater.inflate(getLayoutResId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        onViewBound(view);

        return view;
    }

    private void setupComponent() {

        if (controllerComponent == null) {
            // noinspection unchecked
            ViewType viewProxy = (ViewType) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{getViewClass()}, new ViewProxyInvocationHandler());
            controllerComponent = createControllerComponent(viewProxy);
            if (controllerComponent != null) {
                // noinspection unchecked
                controllerComponent.inject(this);
            }
        }
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    protected void onViewBound(@NonNull View view) {
    }

    protected abstract Class<ViewType> getViewClass();

    protected abstract ControllerComponentType createControllerComponent(ViewType view);

    @Override
    protected void onDestroyView(@NonNull View view) {
        unbinder.unbind();
        unbinder = null;
        super.onDestroyView(view);
    }

    @Override
    protected void onDestroy() {
        ((BasePresenter) presenter).onDestroy();
        presenter = null;
        controllerComponent = null;
        super.onDestroy();
    }

    @Override
    protected final void onSaveInstanceState(@NonNull Bundle state) {

        super.onSaveInstanceState(state);

        Icepick.saveInstanceState(this, state);

        Activity hostActivity = getActivity();
        assert hostActivity != null;

        // Ask the associated presenter to save its state, if any,
        // but just when the controller is about to be destroyed,
        // and not when a configuration change is happening.

        if (!hostActivity.isChangingConfigurations()) {
            BasePresenter presenter = (BasePresenter) getPresenter();
            if (presenter != null) {
                presenter.onSave(state);
            }
        }
    }

    @Override
    protected final void onRestoreInstanceState(@NonNull Bundle state) {

        super.onRestoreInstanceState(state);

        Icepick.restoreInstanceState(this, state);

        BasePresenter presenter = (BasePresenter) getPresenter();
        if (presenter != null) {
            presenter.onRestore(state);
        }
    }

    protected PresenterType getPresenter() {
        return presenter;
    }

    @Override
    public void showError(Throwable throwable) {

        View view = getView();
        assert view != null;

        // TODO: strings.xml
        Snackbar.make(view, "An error has occurred", Snackbar.LENGTH_SHORT).show();
    }

    private class ViewProxyInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {

            // Someone could be trying to invoke a method from Object that is
            // not declared in the view interface, such as toString() or equals().
            // If that's the case, just return what the proxied object would, no
            // matter what the calling thread is.
            List<Method> viewClassMethods = Arrays.asList(getViewClass().getMethods());
            if (!viewClassMethods.contains(method)) {
                return method.invoke(BaseController.this, args);
            }

            // Check that the method returns void.
            Assertion.throwIfConditionNotMet(method.getReturnType().equals(Void.TYPE), "Method " + method.getName() + " declared in " + getViewClass().getName() + " must return void.");

            // TODO: Validate all args are Serializable.

            //Log.e("MATO", "Proxy.Invoke: " + method.getName());

            Runnable invocation = new Runnable() {
                @Override
                public void run() {
                    if (isAttached() && pendingViewCommands.isEmpty()) {
                        try {
                            method.invoke(BaseController.this, args);
                        } catch (Throwable t) {

                        }
                    } else {
                        if (pendingViewCommands.size() < 5) {
                            pendingViewCommands.add(new ViewCommand(method, args));
                            //Log.e("MATO", "Saving pending view command: " + pendingViewCommands.size());
                        }
                    }
                }
            };

            if (Looper.myLooper() == Looper.getMainLooper()) {
                invocation.run();
            } else {
                Handler mainHandler = new Handler(Looper.getMainLooper());

                // At this point, we are sure that the main thread is not going to
                // die, as we the presenter is still alive, which means that it's
                // onDestroy() ............................... <----<----<----<----

                mainHandler.post(invocation); // TODO: Que pasa si justo el main thread se destruye en el proximo loop? Es cuando esto retorna false.
            }

            return null;
        }
    }
}
