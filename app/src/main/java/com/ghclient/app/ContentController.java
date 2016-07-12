package com.ghclient.app;

public abstract class ContentController extends BaseController {

    protected MainController getMainController() {
        return (MainController) getParentController();
    }

}
