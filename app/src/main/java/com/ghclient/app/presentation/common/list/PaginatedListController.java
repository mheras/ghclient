package com.ghclient.app.presentation.common.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.ghclient.app.R;
import com.ghclient.app.presentation.common.base.ContentController;
import com.ghclient.app.presentation.repository.issues.IssueDetailsController;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public abstract class PaginatedListController<PresenterType extends IPaginatedListPresenter<ViewType, ItemType>, ViewType extends IPaginatedListView<ItemType>, ControllerType extends PaginatedListController<PresenterType, ViewType, ControllerType, ItemType, ViewHolderType>, ItemType, ViewHolderType extends ViewHolder<ItemType>> extends ContentController<PresenterType, ViewType, ControllerType> implements IPaginatedListView<ItemType> {

    @BindView(R.id.controller_list_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.controller_list_button)
    Button button;

    private List<ItemType> dataSource;

    protected PaginatedListController() {
        this(null);
    }

    protected PaginatedListController(Bundle args) {
        super(args);
        dataSource = Collections.emptyList();
        // TODO: Bind this Observable to the Controller lifecycle.
        getPresenter().dataSourceUpdates().observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<ItemType>>() {

            @Override
            public void onCompleted() {
                Log.e("MATO", "*****************");
                Log.e("MATO", "onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("MATO", "*****************");
                Log.e("MATO", "onError():");
                Log.e("MATO", e.toString());
            }

            @Override
            public void onNext(List<ItemType> dataSource) {
                PaginatedListController.this.dataSource = dataSource;
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_list, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        setupRecyclerView();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRouter().pushController(RouterTransaction.with(new IssueDetailsController()).pushChangeHandler(new HorizontalChangeHandler()).popChangeHandler(new HorizontalChangeHandler()));
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);
        invalidateLayoutManager();
        recyclerView.setAdapter(new RecyclerView.Adapter<ViewHolderType>() {

            @Override
            public int getItemViewType(int position) {
                return PaginatedListController.this.getItemViewType(dataSource.get(position));
            }

            @Override
            public ViewHolderType onCreateViewHolder(ViewGroup parent, int itemViewType) {
                return PaginatedListController.this.createViewHolder(parent, itemViewType);
            }

            @Override
            public void onBindViewHolder(ViewHolderType viewHolder, int position) {
                viewHolder.bind(dataSource.get(position));
            }

            @Override
            public int getItemCount() {
                return dataSource.size();
            }
        });
    }

    protected final void invalidateLayoutManager() {
        RecyclerView.LayoutManager layoutManager = createLayoutManager();
        recyclerView.setLayoutManager(layoutManager);
    }

    protected abstract RecyclerView.LayoutManager createLayoutManager();

    protected int getItemViewType(ItemType item) {
        return 0;
    }

    protected abstract ViewHolderType createViewHolder(ViewGroup parent, int itemViewType);

    @Override
    public Observable<Void> pageRequests() {
        return Observable.empty();
        //return Observable.from(Arrays.asList((Void) null, (Void) null, (Void) null));

        /*

        https://gist.github.com/nesquena/d09dc68ff07e845cc622

        RxRecyclerView.scrollEvents(recyclerView).filter(new Func1<RecyclerViewScrollEvent, Boolean>() {
            @Override
            public Boolean call(RecyclerViewScrollEvent recyclerViewScrollEvent) {

                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                int lastVisibleItemPosition = 0;
                int totalItemCount = layoutManager.getItemCount();

                if (layoutManager instanceof StaggeredGridLayoutManager) {
                    int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(null);
                    // get maximum element within the list
                    lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);
                } else if (layoutManager instanceof LinearLayoutManager) {
                    lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                } else if (layoutManager instanceof GridLayoutManager) {
                    lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                }



                return true;
            }
        });
        */
    }

    private int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }
}
