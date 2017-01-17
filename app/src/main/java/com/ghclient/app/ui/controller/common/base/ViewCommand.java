package com.ghclient.app.ui.controller.common.base;

import java.io.Serializable;
import java.lang.reflect.Method;

class ViewCommand implements Serializable {

    private String a;

    public ViewCommand(Method method, Object[] args) {
        a = "pepe";
    }
}
