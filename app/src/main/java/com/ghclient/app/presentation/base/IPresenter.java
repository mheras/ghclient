package com.ghclient.app.presentation.base;

public interface IPresenter<ViewType extends IView> {

    void onAttachView(ViewType view);

    void onDetachView();

    void onDestroy();

}
