package com.ghclient.app.presentation.list;

import android.util.Log;

import com.ghclient.app.presentation.base.BasePresenter;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public abstract class ListPresenter<ViewType extends IListView<DataType>, DataType> extends BasePresenter<ViewType> implements IListPresenter<ViewType, DataType> {

    private int page;
    private int perPage;

    public ListPresenter(int perPage) {
        this.page = 1;
        this.perPage = perPage;
    }

    @Override
    public void loadMoreData() {
        createListDataObservable(page, perPage).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<DataType>>() {

            @Override
            public void onCompleted() {
                Log.e("MATO", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("MATO", "onError " + e.toString());
            }

            @Override
            public void onNext(List<DataType> event) {
                Log.e("MATO", "onNext");
            }
        });
    }

    protected abstract Observable<List<DataType>> createListDataObservable(int page, int perPage);
}
