package com.ghclient.app.presentation.view.common.list;

import com.ghclient.app.presentation.view.common.base.IView;

import java.io.Serializable;
import java.util.List;

public interface IListView<ItemType extends Serializable> extends IView {

    void addItems(List<ItemType> items, boolean allItems);

    void clearItems();

}
