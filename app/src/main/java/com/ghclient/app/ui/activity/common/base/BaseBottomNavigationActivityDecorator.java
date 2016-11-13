package com.ghclient.app.ui.activity.common.base;

import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ghclient.app.R;

import java.lang.ref.WeakReference;

public abstract class BaseBottomNavigationActivityDecorator implements IActivityDecorator {

    private WeakReference<BaseActivity> activityRef;

    private BottomNavigationView bottomNavigation;

    public BaseBottomNavigationActivityDecorator(BaseActivity activity) {
        this.activityRef = new WeakReference<>(activity);
    }

    protected BaseActivity getActivity() {
        return activityRef.get();
    }

    @Override
    public View decorateView(View view, ViewGroup parent) {
        View decoration = LayoutInflater.from(getActivity()).inflate(R.layout.decoration_bottom_navigation, parent, false);
        bottomNavigation = (BottomNavigationView) decoration.findViewById(R.id.decoration_bottom_navigation);
        setupBottomNavigation();
        ViewGroup container = (ViewGroup) decoration.findViewById(R.id.decoration_bottom_navigation_container);
        container.addView(view);
        return decoration;
    }

    private void setupBottomNavigation() {
        bottomNavigation.inflateMenu(getBottomNavigationMenuId());
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return onBottomNavigationItemSelected(item.getItemId());
            }
        });
        for (int i = 0; i < bottomNavigation.getMenu().size(); i++) {
            MenuItem item = bottomNavigation.getMenu().getItem(i);
            item.setIcon(getMenuItemIcon(item.getItemId()));
        }
    }

    protected abstract boolean onBottomNavigationItemSelected(@IdRes int menuItemId);

    @MenuRes
    protected abstract int getBottomNavigationMenuId();

    protected Drawable getMenuItemIcon(@IdRes int menuItemId) {
        return null;
    }
}
