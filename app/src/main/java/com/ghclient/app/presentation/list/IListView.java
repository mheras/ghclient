package com.ghclient.app.presentation.list;

import com.ghclient.app.presentation.base.IView;

import java.util.List;

public interface IListView<DataType> extends IView {

    void refreshData(List<DataType> data);

}
