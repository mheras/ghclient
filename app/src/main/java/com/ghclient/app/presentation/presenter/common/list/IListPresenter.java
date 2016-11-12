package com.ghclient.app.presentation.presenter.common.list;

import com.ghclient.app.presentation.presenter.common.base.IPresenter;
import com.ghclient.app.presentation.view.common.list.IListView;

public interface IListPresenter<ViewType extends IListView<ItemType>, ItemType> extends IPresenter<ViewType> {

    void loadItems();

}
