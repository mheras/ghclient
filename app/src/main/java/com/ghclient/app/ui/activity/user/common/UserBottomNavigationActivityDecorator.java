package com.ghclient.app.ui.activity.user.common;

import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;

import com.ghclient.app.R;
import com.ghclient.app.ui.activity.common.base.BaseActivity;
import com.ghclient.app.ui.activity.common.base.BaseBottomNavigationActivityDecorator;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.octicons_typeface_library.Octicons;

public class UserBottomNavigationActivityDecorator extends BaseBottomNavigationActivityDecorator {

    public UserBottomNavigationActivityDecorator(BaseActivity activity) {
        super(activity);
    }

    @Override
    protected boolean onBottomNavigationItemSelected(@IdRes int menuItemId) {
        return false;
        // TODO: Navigate with getActivity().startActivity(...)
    }

    @Override
    protected int getBottomNavigationMenuId() {
        return R.menu.menu_bottom_navigation_main;
    }

    @Override
    protected Drawable getMenuItemIcon(@IdRes int menuItemId) {

        switch (menuItemId) {
            case R.id.menu_bottom_navigation_main_events:
                return new IconicsDrawable(getActivity()).icon(Octicons.Icon.oct_home);
            case R.id.menu_bottom_navigation_main_pull_requests:
                return new IconicsDrawable(getActivity()).icon(Octicons.Icon.oct_git_pull_request);
            case R.id.menu_bottom_navigation_main_issues:
                return new IconicsDrawable(getActivity()).icon(Octicons.Icon.oct_issue_opened);
            case R.id.menu_bottom_navigation_main_repositories:
                return new IconicsDrawable(getActivity()).icon(Octicons.Icon.oct_repo);
        }

        return super.getMenuItemIcon(menuItemId);
    }
}
