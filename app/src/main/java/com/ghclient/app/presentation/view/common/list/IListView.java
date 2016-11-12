package com.ghclient.app.presentation.view.common.list;

import com.ghclient.app.presentation.view.common.base.IView;

import java.util.List;

public interface IListView<ItemType> extends IView {

    void setItems(List<ItemType> items, boolean allItems);

}
