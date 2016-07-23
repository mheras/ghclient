package com.ghclient.app.presentation.main;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.ghclient.app.R;
import com.ghclient.app.di.AppComponent;
import com.ghclient.app.di.base.BaseControllerComponent;
import com.ghclient.app.presentation.base.BaseController;
import com.ghclient.app.presentation.user.events.EventsController;

import butterknife.BindView;

public class MainController extends BaseController<MainPresenter, MainView, MainController> implements MainView {

    private final static String CONTENT_ROUTER_TAG = "content";

    @BindView(R.id.controller_main_content_container)
    ViewGroup contentContainer;

    @BindView(R.id.controller_main_toolbar)
    Toolbar toolbar;

    private Router contentRouter;

    @Override
    protected BaseControllerComponent<MainController> createControllerComponent(AppComponent appComponent) {
        return null;
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_main, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {

        super.onViewBound(view);

        getAppCompatActivity().setSupportActionBar(toolbar);
        ActionBar actionBar = getAppCompatActivity().getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        contentRouter = getChildRouter(contentContainer, CONTENT_ROUTER_TAG);
        if (!contentRouter.hasRootController()) {
            contentRouter.setRoot(RouterTransaction.with(new EventsController("mheras")));
        }

        // TODO: new IconicsDrawable(this).icon(Octicons.Icon.oct_code).color(Color.WHITE)
    }
}
