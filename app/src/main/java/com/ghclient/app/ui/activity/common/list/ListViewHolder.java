package com.ghclient.app.ui.activity.common.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public abstract class ListViewHolder<ItemType> extends RecyclerView.ViewHolder {

    public ListViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    protected abstract void bind(ItemType item);

}
