package com.ghclient.app.presentation.common.list;

import com.ghclient.app.presentation.common.base.BasePresenter;
import com.ghclient.app.presentation.common.base.PresenterEvent;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public abstract class PaginatedListPresenter<ViewType extends IPaginatedListView<ItemType>, ItemType> extends BasePresenter<ViewType> implements IPaginatedListPresenter<ViewType, ItemType> {

    private int perPage;

    public PaginatedListPresenter(ViewType view, final int perPage) {
        super(view);
        this.perPage = perPage;
    }

    @Override
    public Observable<List<ItemType>> dataSourceUpdates() {

        // TODO: Bind the Observable to the Presenter lifecycle!

        return lifecycle().filter(new Func1<PresenterEvent, Boolean>() {
            @Override
            public Boolean call(PresenterEvent presenterEvent) {
                return presenterEvent == PresenterEvent.CREATE;
            }
        }).map(new Func1<PresenterEvent, Void>() {

            @Override
            public Void call(PresenterEvent presenterEvent) {
                return null;
            }
        }).mergeWith(getView().pageRequests()).map(new Func1<Void, Integer>() {
            @Override
            public Integer call(Void pageRequest) {
                return 1;
            }
        }).scan(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer previousPage, Integer unused) {
                return previousPage + 1;
            }
        }).flatMap(new Func1<Integer, Observable<List<ItemType>>>() {
            @Override
            public Observable<List<ItemType>> call(Integer page) {
                return pageData(page, perPage);
            }
        }).subscribeOn(Schedulers.io());
    }

    protected abstract Observable<List<ItemType>> pageData(int page, int perPage);
}
