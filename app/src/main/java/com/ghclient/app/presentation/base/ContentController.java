package com.ghclient.app.presentation.base;

import android.os.Bundle;

public abstract class ContentController<P extends BasePresenter<V>, V extends IView> extends BaseController<P, V> {

    protected ContentController() {}

    protected ContentController(Bundle args) {
        super(args);
    }

}
