package com.uber.rib.root.common.navigation_drawer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxMenuItem;
import com.jakewharton.rxbinding2.view.RxView;
import com.uber.rib.core.Initializer;
import com.uber.rib.core.RxActivityEvents;
import com.uber.rib.tutorial1.R;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Top level view for {@link NavigationDrawerBuilder.NavigationDrawerScope}.
 */
class NavigationDrawerView extends NavigationView implements NavigationDrawerInteractor.NavigationDrawerPresenter {

//    @Inject
//    NavigationDrawerInteractor.Listener listener1;

    public NavigationDrawerView(Context context) {
        this(context, null);
    }

    public NavigationDrawerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationDrawerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Nullable
    MenuItem mCurrentMenuItemClick;
    @Nullable
    View mCurrentMenuItemClickView;
    NavigationView navigationView;
    DrawerLayout mDrawerLayout;

    @Initializer
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        navigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        navigationView.setNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.list_navigation_menu_item: {
//                        listener1.requestMenuItemListClick(1);
                        break;
                    }
                    case R.id.statistics_navigation_menu_item: {
//                        listener1.requestMenuItemStatisticClick(0);

                        break;
                    }

                }
                return false;
            }
        });

    }



    @Override
    public Observable<Integer> menuItemListClick() {
        MenuItem menuItem = navigationView.getMenu().getItem(0);

        return RxMenuItem.clicks(menuItem)
                .map(new Function<Object, Integer>() {
                    @Override
                    public Integer apply(Object o) throws Exception {
                        return R.id.list_navigation_menu_item;
                    }
                });
    }

    @Override
    public Observable<Integer> menuItemStatisticClick() {
//        View view = navigationView.getMenu().getItem(1).getActionView();
        MenuItem menuItem = navigationView.getMenu().getItem(1);


        return RxMenuItem.clicks(menuItem)
                .map(new Function<Object, Integer>() {
                    @Override
                    public Integer apply(Object o) throws Exception {
                        return R.id.statistics_navigation_menu_item;
                    }
                });
    }



}
