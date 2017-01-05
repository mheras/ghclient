package com.ghclient.app.ui.controller.common.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.Serializable;

import butterknife.ButterKnife;

public abstract class ListViewHolder<ItemType extends Serializable> extends RecyclerView.ViewHolder {

    public ListViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    protected abstract void bind(ItemType item);

}
