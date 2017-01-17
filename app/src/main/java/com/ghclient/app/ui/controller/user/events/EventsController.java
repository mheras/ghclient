package com.ghclient.app.ui.controller.user.events;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ghclient.app.App;
import com.ghclient.app.R;
import com.ghclient.app.di.user.events.DaggerIEventsComponent;
import com.ghclient.app.di.user.events.EventsModule;
import com.ghclient.app.di.user.events.IEventsComponent;
import com.ghclient.app.model.entity.Event;
import com.ghclient.app.presentation.presenter.user.events.IEventsPresenter;
import com.ghclient.app.presentation.view.user.events.IEventsView;
import com.ghclient.app.ui.controller.common.list.ListController;
import com.ghclient.app.ui.controller.common.list.ListViewHolder;

import butterknife.BindView;

public class EventsController extends ListController<IEventsPresenter, IEventsView, IEventsComponent, Event, EventsController.EventsViewHolder> implements IEventsView {

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected EventsController.EventsViewHolder createViewHolder(ViewGroup parent, int itemViewType) {
        return new EventsController.EventsViewHolder(getActivity().getLayoutInflater().inflate(R.layout.cell_event, parent, false));
    }

    @Override
    protected Class<IEventsView> getViewClass() {
        return IEventsView.class;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.controller_events;
    }

    @Override
    protected IEventsComponent createControllerComponent(IEventsView view) {
        return DaggerIEventsComponent.builder().iAppComponent(App.getAppComponent()).eventsModule(new EventsModule(view)).build();
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
