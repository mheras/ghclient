package com.ghclient.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.rxlifecycle.RxController;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseController extends RxController {

    private Unbinder unbinder;

    protected BaseController() {
    }

    protected BaseController(Bundle args) {
        super(args);
    }

    protected abstract View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container);

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflateView(inflater, container);
        unbinder = ButterKnife.bind(this, view);
        onViewBound(view);
        return view;
    }

    protected void onViewBound(@NonNull View view) {
    }

    @Override
    protected void onDestroyView(View view) {
        super.onDestroyView(view);
        unbinder.unbind();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.refWatcher.watch(this);
    }

    protected AppCompatActivity getAppCompatActivity() {
        return (AppCompatActivity) getActivity();
    }
}
