package com.ghclient.app.di.base;

public interface BaseComponent<InjectableType> {
    void inject(InjectableType injectableType);
}
