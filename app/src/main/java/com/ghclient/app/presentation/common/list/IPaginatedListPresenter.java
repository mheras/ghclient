package com.ghclient.app.presentation.common.list;

import com.ghclient.app.presentation.common.base.IPresenter;

import java.util.List;

import rx.Observable;

public interface IPaginatedListPresenter<ViewType extends IPaginatedListView<ItemType>, ItemType> extends IPresenter<ViewType> {

    Observable<List<ItemType>> dataSourceUpdates();

}
