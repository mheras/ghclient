package com.ghclient.app.di.base;

public interface IComponent<InjectableType> {
    void inject(InjectableType injectableType);
}
