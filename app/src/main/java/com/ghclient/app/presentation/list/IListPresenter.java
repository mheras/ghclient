package com.ghclient.app.presentation.list;

import com.ghclient.app.presentation.base.IPresenter;

public interface IListPresenter<ViewType extends IListView<DataType>, DataType> extends IPresenter<ViewType> {

    void loadMoreData();

}
