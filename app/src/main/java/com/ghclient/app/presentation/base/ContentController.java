package com.ghclient.app.presentation.base;

import android.os.Bundle;

public abstract class ContentController<PresenterType extends BasePresenter<ViewType>, ViewType extends IView, ControllerType extends ContentController<PresenterType, ViewType, ControllerType>> extends BaseController<PresenterType, ViewType, ControllerType> {

    protected ContentController() {}

    protected ContentController(Bundle args) {
        super(args);
    }

}
