package com.ghclient.app.presentation.common.list;

import com.ghclient.app.presentation.common.base.IView;

import rx.Observable;

public interface IPaginatedListView<ItemType> extends IView {

    Observable<Void> pageRequests();

}
