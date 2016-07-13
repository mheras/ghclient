package com.ghclient.app.presentation.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ghclient.app.R;
import com.ghclient.app.presentation.base.ContentController;

import butterknife.BindView;

public abstract class ListController<P extends ListPresenter<V>, V extends IListView> extends ContentController<P, V> implements IListView {

    @BindView(R.id.controller_list_recycler_view)
    RecyclerView recyclerView;

    protected ListController() {}

    protected ListController(Bundle args) {
        super(args);
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_list, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
    }
}
