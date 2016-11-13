package com.ghclient.app.ui.activity.user.events;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.ghclient.app.R;
import com.ghclient.app.model.entity.Event;
import com.ghclient.app.presentation.presenter.user.events.EventsPresenter;
import com.ghclient.app.presentation.presenter.user.events.IEventsPresenter;
import com.ghclient.app.presentation.view.user.events.IEventsView;
import com.ghclient.app.ui.activity.common.base.IActivityDecorator;
import com.ghclient.app.ui.activity.common.list.ListActivity;
import com.ghclient.app.ui.activity.common.list.ListViewHolder;
import com.ghclient.app.ui.activity.user.common.UserBottomNavigationActivityDecorator;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;

public class EventsActivity extends ListActivity<Event, EventsActivity.EventsViewHolder, IEventsPresenter> implements IEventsView {

    @InjectPresenter
    EventsPresenter presenter;

    @Override
    protected List<IActivityDecorator> createActivityDecorators() {
        return Collections.<IActivityDecorator>singletonList(new UserBottomNavigationActivityDecorator(this));
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    protected EventsViewHolder createViewHolder(ViewGroup parent, int itemViewType) {
        return new EventsViewHolder(getLayoutInflater().inflate(R.layout.cell_event, parent, false));
    }

    @Override
    protected IEventsPresenter getPresenter() {
        return presenter;
    }

    // TODO: It has package access for ButterKnife. Can we move it somewhere else?
    class EventsViewHolder extends ListViewHolder<Event> {

        @BindView(R.id.cell_event_type)
        TextView typeTextView;

        public EventsViewHolder(View view) {
            super(view);
        }

        @Override
        protected void bind(Event item) {
            typeTextView.setText(item.getType());
        }
    }

}
