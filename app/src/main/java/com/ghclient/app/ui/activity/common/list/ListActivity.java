package com.ghclient.app.ui.activity.common.list;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.ghclient.app.R;
import com.ghclient.app.presentation.presenter.common.list.IListPresenter;
import com.ghclient.app.presentation.view.common.list.IListView;
import com.ghclient.app.ui.activity.common.base.BaseActivity;
import com.paginate.Paginate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public abstract class ListActivity<ItemType, ListViewHolderType extends ListViewHolder<ItemType>, ListPresenterType extends IListPresenter<? extends IListView, ItemType>> extends BaseActivity<ListPresenterType> implements IListView<ItemType> {

    @BindView(R.id.activity_list_recycler_view)
    RecyclerView recyclerView;

    private List<ItemType> dataSource = new ArrayList<>();

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    protected void onViewBound(Bundle savedInstanceState) {
        super.onViewBound(savedInstanceState);
        setupRecyclerView();
    }

    private void setupRecyclerView() {

        recyclerView.setHasFixedSize(true);
        invalidateLayoutManager();
        recyclerView.setAdapter(new RecyclerView.Adapter<ListViewHolderType>() {

            @Override
            public int getItemViewType(int position) {
                return ListActivity.this.getItemViewType(dataSource.get(position));
            }

            @Override
            public ListViewHolderType onCreateViewHolder(ViewGroup parent, int itemViewType) {
                return ListActivity.this.createViewHolder(parent, itemViewType);
            }

            @Override
            public void onBindViewHolder(ListViewHolderType viewHolder, int position) {
                viewHolder.bind(dataSource.get(position));
            }

            @Override
            public int getItemCount() {
                return dataSource.size();
            }
        });

        Paginate.with(recyclerView, new Paginate.Callbacks() {

            @Override
            public void onLoadMore() {
                getPresenter().loadItems();
            }

            @Override
            public boolean isLoading() {
                return false;
            }

            @Override
            public boolean hasLoadedAllItems() {
                return true;
            }

        }).build();
    }

    @Override
    public void setItems(List<ItemType> items, boolean allItems) {

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
