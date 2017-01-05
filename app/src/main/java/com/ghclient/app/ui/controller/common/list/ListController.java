package com.ghclient.app.ui.controller.common.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ghclient.app.R;
import com.ghclient.app.di.base.IControllerComponent;
import com.ghclient.app.presentation.presenter.common.list.IListPresenter;
import com.ghclient.app.presentation.view.common.list.IListView;
import com.ghclient.app.ui.controller.common.base.BaseController;
import com.ghclient.app.ui.util.Assertion;
import com.paginate.Paginate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public abstract class ListController<PresenterType extends IListPresenter<ItemType>, ViewType extends IListView<ItemType>, ControllerComponentType extends IControllerComponent, ItemType extends Serializable, ListViewHolderType extends ListViewHolder<ItemType>> extends BaseController<PresenterType, ViewType, ControllerComponentType> implements IListView<ItemType> {

    @BindView(R.id.controller_list_recycler_view)
    RecyclerView recyclerView;

    private List<ItemType> items = new ArrayList<>();

    private boolean allItems;

    private boolean loading;

    public ListController() {
        super(null);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        Assertion.throwIfConditionNotMet(recyclerView != null, "A RecyclerView with id = 'controller_list_recycler_view' must be part of the inflated view hierarchy.");
        setupRecyclerView();
    }

    protected void setHasFixedSize(boolean fixedSize) {
        recyclerView.setHasFixedSize(fixedSize);
    }

    private void setupRecyclerView() {

        invalidateLayoutManager();

        recyclerView.setAdapter(new RecyclerView.Adapter<ListViewHolderType>() {

            @Override
            public int getItemViewType(int position) {
                return ListController.this.getItemViewType(items.get(position));
            }

            @Override
            public ListViewHolderType onCreateViewHolder(ViewGroup parent, int itemViewType) {
                return ListController.this.createViewHolder(parent, itemViewType);
            }

            @Override
            public void onBindViewHolder(ListViewHolderType viewHolder, int position) {
                viewHolder.bind(items.get(position));
            }

            @Override
            public int getItemCount() {
                return items.size();
            }
        });

        if (isPaginationEnabled()) {
            Paginate.with(recyclerView, new Paginate.Callbacks() {

                @Override
                public void onLoadMore() {
                    loading = true;
                    getPresenter().loadItems();
                }

                @Override
                public boolean isLoading() {
                    return loading;
                }

                @Override
                public boolean hasLoadedAllItems() {
                    return allItems;
                }

            }).build();
        }
    }

    protected boolean isPaginationEnabled() {
        return true;
    }

    @Override
    public void addItems(List<ItemType> items, boolean allItems) {
        loading = false;
        this.allItems = allItems;
        this.items.addAll(items);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void clearItems() {
        loading = false;
        items.clear();
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    protected final void invalidateLayoutManager() {
        RecyclerView.LayoutManager layoutManager = createLayoutManager();
        recyclerView.setLayoutManager(layoutManager);
    }

    protected abstract RecyclerView.LayoutManager createLayoutManager();

    protected int getItemViewType(ItemType item) {
        return 0;
    }

    protected abstract ListViewHolderType createViewHolder(ViewGroup parent, int itemViewType);
}
