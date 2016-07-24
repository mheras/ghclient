package com.ghclient.app.presentation.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.ghclient.app.R;
import com.ghclient.app.presentation.base.ContentController;
import com.ghclient.app.presentation.repository.issues.IssueDetailsController;

import java.util.List;

import butterknife.BindView;

public abstract class ListController<PresenterType extends IListPresenter<ViewType, DataType>, ViewType extends IListView<DataType>, ControllerType extends ListController<PresenterType, ViewType, ControllerType, DataType>, DataType> extends ContentController<PresenterType, ViewType, ControllerType> implements IListView<DataType> {

    @BindView(R.id.controller_list_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.controller_list_button)
    Button button;

    protected ListController() {
        this(null);
    }

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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRouter().pushController(RouterTransaction.with(new IssueDetailsController()).pushChangeHandler(new HorizontalChangeHandler()).popChangeHandler(new HorizontalChangeHandler()));
            }
        });
    }

    @Override
    public void refreshData(List<DataType> content) {
        // TODO: Implement
    }
}
