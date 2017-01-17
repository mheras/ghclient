package com.ghclient.app.ui.controller.user.home;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.ghclient.app.App;
import com.ghclient.app.R;
import com.ghclient.app.di.user.home.DaggerIHomeComponent;
import com.ghclient.app.di.user.home.HomeModule;
import com.ghclient.app.di.user.home.IHomeComponent;
import com.ghclient.app.presentation.presenter.user.home.IHomePresenter;
import com.ghclient.app.presentation.view.user.home.IHomeView;
import com.ghclient.app.ui.controller.common.base.BaseController;
import com.ghclient.app.ui.controller.user.events.EventsController;

import butterknife.BindView;

public class HomeController extends BaseController<IHomePresenter, IHomeView, IHomeComponent> implements IHomeView {

    @BindView(R.id.controller_home_events_button)
    Button eventsButton;

    @BindView(R.id.controller_home_counter_text_view)
    TextView counterTextView;

    @Override
    protected Class<IHomeView> getViewClass() {
        return IHomeView.class;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.controller_home;
    }

    @Override
    protected IHomeComponent createControllerComponent(IHomeView view) {
        return DaggerIHomeComponent.builder().iAppComponent(App.getAppComponent()).homeModule(new HomeModule(view)).build();
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        eventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getRouter().pushController(RouterTransaction.with(new EventsController()).pushChangeHandler(new HorizontalChangeHandler()).popChangeHandler(new HorizontalChangeHandler()));
            }
        });
    }

    @Override
    public void setCounter(int counter) {
        counterTextView.setText(String.valueOf(counter));
    }
}
