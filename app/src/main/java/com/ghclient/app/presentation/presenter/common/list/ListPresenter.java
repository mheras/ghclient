package com.ghclient.app.presentation.presenter.common.list;

import com.ghclient.app.di.base.IPresenterComponent;
import com.ghclient.app.presentation.presenter.common.base.BasePresenter;
import com.ghclient.app.presentation.view.common.list.IListView;

public abstract class ListPresenter<ViewType extends IListView<ItemType>, ItemType, PresenterComponentType extends IPresenterComponent> extends BasePresenter<ViewType, PresenterComponentType> implements IListPresenter<ViewType, ItemType> {

    @Override
    public void loadItems() {

    }
}
