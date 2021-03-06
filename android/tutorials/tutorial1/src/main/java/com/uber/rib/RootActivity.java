/*
 * Copyright (C) 2017. Uber Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uber.rib;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxMenuItem;
import com.jakewharton.rxbinding2.view.RxView;
import com.uber.rib.core.RibActivity;
import com.uber.rib.core.ViewRouter;
import com.uber.rib.root.RootBuilder;
import com.uber.rib.root.RootInteractor;
import com.uber.rib.root.RootRouter;
import com.uber.rib.root.task_act.TaskActInteractor;
import com.uber.rib.tutorial1.R;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.functions.Function;

/**
 * The sample app's single activity.
 */
public class RootActivity extends RibActivity {

    @Nullable
    Toolbar mToolbar;
    @Nullable
    DrawerLayout mDrawerLayout;

    Integer resourceOptionsMenuId = -1;

    @Nullable
    RootRouter mRootRouter = null;

    @Nullable
    public Menu mMenu;


    public InstanceRootListener instanceRootListener = new InstanceRootListener();

    @Nullable public PopupMenu mPopupMenu;

  @SuppressWarnings("unchecked")
  @Override
  protected ViewRouter<?, ?, ?> createRouter(ViewGroup parentViewGroup) {

    RootBuilder rootBuilder = new RootBuilder(new RootBuilder.ParentComponent() {
      @Override
      public RootInteractor.RootListener listener() {
        return instanceRootListener;
      }
    }, this);



    RootRouter rootRouter = rootBuilder.build(parentViewGroup);
    mRootRouter = rootRouter;
    return rootRouter;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(resourceOptionsMenuId, menu);
    mMenu = menu;
    return super.onCreateOptionsMenu(menu);
  }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
          case R.id.menu_filter: {
              View view = findViewById(R.id.menu_filter);
              PopupMenu popupMenu = new PopupMenu(this, this.findViewById(R.id.menu_filter));
              popupMenu.getMenuInflater().inflate(R.menu.filter_tasks, popupMenu.getMenu());
              popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                  @Override
                  public boolean onMenuItemClick(MenuItem item) {
                      if (mRootRouter != null) {
                          mRootRouter.requestChildRibListenPopupMenuItemSelected(item.getItemId());
                      }
                      return false;
                  }
              });
              popupMenu.show();
              break;
          }
          default: {
              if (mRootRouter != null) {
                  mRootRouter.requestChildRibListenMenuItemSelected(item.getItemId());
              }
          }
      }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
      if (mRootRouter != null) {
          boolean isHomeApp = mRootRouter.requestChildRibListenBackPress();
          if (isHomeApp) {
              moveTaskToBack(false);
          }

      }
    }


  class InstanceRootListener implements RootInteractor.RootListener {

    @SuppressLint("ResourceAsColor")
//    @Override
//    public void suggestSetupSupportActionBar(Toolbar toolbar) {
//      ActionBar ab = getSupportActionBar();
////      ab.setHomeAsUpIndicator(R.drawable.ic_menu);
//      if (mRootRouter != null) {
//          setupHomeAsUpIndicator(ab, mRootRouter.listRouter);
//      }
//      ab.setDisplayHomeAsUpEnabled(true);
//      ab.setBackgroundDrawable(new ColorDrawable(R.drawable.colorPrimaryDrawable));
//      mToolbar = toolbar;
//    }



    @Override
    public void suggestSetupNavigationDrawer(DrawerLayout drawerLayout) {
      drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
      mDrawerLayout = drawerLayout;

    }

    @Override
    public void suggestAddMenuTaskFragment(Integer resourceMenuId) {

      resourceOptionsMenuId = resourceMenuId;


    }

      @Override
      public void suggestSetupActionBar(int stringTitleId, boolean isShowMenuOptions) {
          if (mMenu != null) {
              mMenu.findItem(R.id.menu_clear).setVisible(isShowMenuOptions);
              mMenu.findItem(R.id.menu_filter).setVisible(isShowMenuOptions);
              mMenu.findItem(R.id.menu_refresh).setVisible(isShowMenuOptions);

          }

          ActionBar ab = getSupportActionBar();
          ab.setTitle(stringTitleId);
          ab.setDisplayHomeAsUpEnabled(true);
          ab.setBackgroundDrawable(new ColorDrawable(R.drawable.colorPrimaryDrawable));

          if (mRootRouter != null) {
              setupHomeAsUpIndicator(ab, mRootRouter.listRouter);
          }
      }


  }

    private void setupHomeAsUpIndicator(ActionBar ab, ArrayList<ViewRouter> listRouter) {
        if (listRouter.size() == 1) {
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        } else if (listRouter.size() > 1) {
            ab.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        }
    }

}

