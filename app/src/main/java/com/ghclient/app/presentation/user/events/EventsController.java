package com.ghclient.app.presentation.user.events;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ghclient.app.R;
import com.ghclient.app.di.AppComponent;
import com.ghclient.app.di.base.IControllerComponent;
import com.ghclient.app.di.user.events.DaggerEventsComponent;
import com.ghclient.app.di.user.events.EventsModule;
import com.ghclient.app.model.entity.Event;
import com.ghclient.app.presentation.common.list.PaginatedListController;
import com.ghclient.app.presentation.common.list.ViewHolder;
import com.ghclient.app.util.BundleBuilder;

import butterknife.BindView;

public class EventsController extends PaginatedListController<IEventsPresenter, IEventsView, EventsController, Event, EventsController.EventsViewHolder> implements IEventsView {

    private final static String USERNAME_KEY = "username";

    public EventsController(String username) {
        this(new BundleBuilder(new Bundle()).putString(USERNAME_KEY, username).build());
    }

    public EventsController(Bundle args) {
        super(args);
        String username = args.getString(USERNAME_KEY);
    }

    @Override
    protected IControllerComponent<EventsController> createControllerComponent(AppComponent appComponent) {
        return DaggerEventsComponent.builder().appComponent(appComponent).eventsModule(new EventsModule(this)).build();
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected EventsViewHolder createViewHolder(ViewGroup parent, int itemViewType) {
        return new EventsViewHolder(getActivity().getLayoutInflater().inflate(R.layout.cell_event, parent, false));
    }

    // TODO: It has package access for ButterKnife. Can we move it somewhere else?
    class EventsViewHolder extends ViewHolder<Event> {

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
