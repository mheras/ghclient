package com.ghclient.app.ui.activity.common.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.ghclient.app.R;
import com.ghclient.app.presentation.presenter.common.base.IPresenter;
import com.ghclient.app.presentation.view.common.base.IView;
import com.mikepenz.iconics.context.IconicsLayoutInflater;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity<PresenterType extends IPresenter> extends MvpAppCompatActivity implements IView {

    private ViewGroup container;

    @BindView(R.id.activity_base_toolbar)
    Toolbar toolbar;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        container = (ViewGroup) findViewById(R.id.activity_base_container);
        container.addView(LayoutInflater.from(this).inflate(getActivityLayoutId(), container, false));
        ButterKnife.bind(this);
        onViewBound(savedInstanceState);
    }

    protected abstract PresenterType getPresenter();

    protected final ViewGroup getContainer() {
        return container;
    }

    @Override
    public void showError(Throwable throwable) {
        // TODO: strings.xml
        Snackbar.make(getContainer(), "An error has occurred", Snackbar.LENGTH_SHORT).show();
    }

    @LayoutRes
    protected abstract int getActivityLayoutId();

    protected void onViewBound(Bundle savedInstanceState) {
        setupToolbar();

        // TODO: new IconicsDrawable(this).icon(Octicons.Icon.oct_code).color(Color.WHITE)
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

}
