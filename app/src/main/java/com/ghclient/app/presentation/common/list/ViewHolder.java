package com.ghclient.app.presentation.common.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public abstract class ViewHolder<ItemType> extends RecyclerView.ViewHolder {

    public ViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    protected abstract void bind(ItemType item);

}
