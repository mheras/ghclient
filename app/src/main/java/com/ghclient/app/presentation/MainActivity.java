package com.ghclient.app.presentation;

import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.ghclient.app.R;
import com.ghclient.app.presentation.main.MainController;
import com.mikepenz.iconics.context.IconicsLayoutInflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

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

        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new MainController()));
        }
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
