package com.ghclient.app.presentation.view.common.base;

import com.arellomobile.mvp.MvpView;

public interface IView extends MvpView {
    void showError(Throwable throwable);
}
