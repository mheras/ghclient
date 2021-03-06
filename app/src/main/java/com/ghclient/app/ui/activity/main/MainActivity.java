package com.ghclient.app.ui.activity.main;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.ghclient.app.R;
import com.ghclient.app.ui.controller.user.home.HomeController;
import com.mikepenz.iconics.context.IconicsLayoutInflater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private Router router;

    @BindView(R.id.activity_main_toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_main_app_bar)
    AppBarLayout appBar;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {

        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupToolbar();

        ViewGroup container = (ViewGroup) findViewById(R.id.activity_main_container);

        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new HomeController()));
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        router = null;
        super.onDestroy();
    }
}
