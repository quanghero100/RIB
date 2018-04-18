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
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.uber.rib.core.RibActivity;
import com.uber.rib.core.ViewRouter;
import com.uber.rib.root.RootBuilder;
import com.uber.rib.root.RootInteractor;
import com.uber.rib.tutorial1.R;

import javax.annotation.Resource;

/**
 * The sample app's single activity.
 */
public class RootActivity extends RibActivity{

    @Nullable
    Toolbar mToolbar;
    @Nullable
    DrawerLayout mDrawerLayout;

  @SuppressWarnings("unchecked")
  @Override
  protected ViewRouter<?, ?, ?> createRouter(ViewGroup parentViewGroup) {
    RootBuilder rootBuilder = new RootBuilder(new RootBuilder.ParentComponent() {
      @Override
      public RootInteractor.RootListener listener() {
        return new InstanceRootListener();
      }
    }, this);



    return rootBuilder.build(parentViewGroup);
  }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      mDrawerLayout = findViewById(R.id.drawer_layout);
      switch (item.getItemId()){
          case android.R.id.home: {
            if (mDrawerLayout != null) {
                if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
                  mDrawerLayout.closeDrawer(Gravity.START);
                } else {
                  mDrawerLayout.openDrawer(Gravity.START);
                }
            }
            return true;
          }
      }
        return super.onOptionsItemSelected(item);
    }

    RibActivity mActivity = this;



  class InstanceRootListener implements RootInteractor.RootListener {


    @SuppressLint("ResourceAsColor")
    @Override
    public void suggestSetupSupportActionBar(Toolbar toolbar) {
//      mActivity.setSupportActionBar(toolbar);

      ActionBar ab = getSupportActionBar();
      ab.setHomeAsUpIndicator(R.drawable.ic_menu);
      ab.setDisplayHomeAsUpEnabled(true);
//      ab.setDisplayShowHomeEnabled(true);
//      ab.setTitle("hsdfghdf");
//      ab.setSubtitle("sdfsd");
      ab.setBackgroundDrawable(new ColorDrawable(R.drawable.colorPrimaryDrawable));
      mToolbar = toolbar;

    }




    @Override
    public void suggestSetupNavigationDrawer(DrawerLayout drawerLayout) {
      drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
      mDrawerLayout = drawerLayout;

    }



//    @Override
//    public void suggestHomeMenuClick() {
//      if (mDrawerLayout != null) {
//        if (mDrawerLayout.isDrawerOpen(Gravity.END)) {
//          mDrawerLayout.closeDrawer(Gravity.START);
//        } else {
//          mDrawerLayout.openDrawer(Gravity.END);
//        }
//      }
//    }

  }
  }

