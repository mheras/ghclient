package com.ghclient.app.presentation.presenter.common.list;

import com.ghclient.app.presentation.presenter.common.base.IPresenter;

import java.io.Serializable;

public interface IListPresenter<ItemType extends Serializable> extends IPresenter {

    void loadItems();

    void onItemSelected(ItemType item);

}
