package com.ghclient.app.presentation.presenter.common.list;

import android.util.Log;

import com.ghclient.app.presentation.presenter.common.base.BasePresenter;
import com.ghclient.app.presentation.view.common.list.IListView;

import java.io.Serializable;

public abstract class ListPresenter<ViewType extends IListView<ItemType>, ItemType extends Serializable> extends BasePresenter<ViewType> implements IListPresenter<ItemType> {

    public ListPresenter(ViewType view) {
        super(view);
    }

    @Override
    public void loadItems() {
        Log.e("MATO", "loadItems");
    }

    @Override
    public void onItemSelected(ItemType item) {
        Log.e("MATO", "onItemSelected: " + item);
    }
}
