package com.ghclient.app;

import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;

import butterknife.BindView;

public class MainController extends BaseController {

    private final static String CONTENT_ROUTER_TAG = "content";
    private final static String DRAWER_ROUTER_TAG = "drawer";

    @BindView(R.id.controller_main_drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.controller_main_drawer_container)
    ViewGroup drawerContainer;

    @BindView(R.id.controller_main_content_container)
    ViewGroup contentContainer;

    @BindView(R.id.controller_main_toolbar)
    Toolbar toolbar;

    private Router contentRouter;
    private Router drawerRouter;
    private ActionBarDrawerToggle drawerToggle;

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
            contentRouter.setRoot(RouterTransaction.with(new HomeController()));
        }

        drawerRouter = getChildRouter(drawerContainer, DRAWER_ROUTER_TAG);
        if (!drawerRouter.hasRootController()) {
            drawerRouter.setRoot(RouterTransaction.with(new DrawerController()));
        }

        drawerToggle = new ActionBarDrawerToggle(getAppCompatActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, 0);
            }
        };

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        // // new IconicsDrawable(this).icon(Octicons.Icon.oct_code).color(Color.WHITE)
    }

    @Override
    public boolean handleBack() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
        return super.handleBack();
    }

    public void setDrawerEnabled(boolean enabled) {
        drawerLayout.setDrawerLockMode(enabled ? DrawerLayout.LOCK_MODE_UNLOCKED : DrawerLayout.LOCK_MODE_LOCKED_CLOSED, GravityCompat.START);
        drawerToggle.setDrawerIndicatorEnabled(enabled);
    }
}
