package com.ghclient.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.context.IconicsLayoutInflater;
import com.mikepenz.octicons_typeface_library.Octicons;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_main_bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    @BindView(R.id.activity_main_container)
    ViewGroup container;

    private Router router;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new HomeController()));
        }

        bottomNavigationBar
                .addItem(new BottomNavigationItem(new IconicsDrawable(this).icon(Octicons.Icon.oct_code).color(Color.WHITE), "Code"))
                .addItem(new BottomNavigationItem(new IconicsDrawable(this).icon(Octicons.Icon.oct_issue_opened).color(Color.WHITE), "Issues"))
                .addItem(new BottomNavigationItem(new IconicsDrawable(this).icon(Octicons.Icon.oct_git_pull_request).color(Color.WHITE), "Pull Requests"))
                .addItem(new BottomNavigationItem(new IconicsDrawable(this).icon(Octicons.Icon.oct_book).color(Color.WHITE), "Wiki"))
                .addItem(new BottomNavigationItem(new IconicsDrawable(this).icon(Octicons.Icon.oct_pulse).color(Color.WHITE), "Pulse"))
                .initialise();
    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
