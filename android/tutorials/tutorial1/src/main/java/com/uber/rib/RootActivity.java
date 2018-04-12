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
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.uber.rib.core.RibActivity;
import com.uber.rib.core.ViewRouter;
import com.uber.rib.root.RootBuilder;
import com.uber.rib.root.RootInteractor;
import com.uber.rib.tutorial1.R;

/**
 * The sample app's single activity.
 */
public class RootActivity extends RibActivity{

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

  RibActivity mActivity = this;
  class InstanceRootListener implements RootInteractor.RootListener {

    @SuppressLint("ResourceAsColor")
    @Override
    public void suggestSetupSupportActionBar(Toolbar toolbar) {
//      mActivity.setSupportActionBar(toolbar);

      ActionBar ab = getSupportActionBar();
      ab.setHomeAsUpIndicator(R.drawable.ic_menu);
      ab.setDisplayHomeAsUpEnabled(true);
      ab.setDisplayShowHomeEnabled(true);
//      ab.setTitle("hsdfghdf");
//      ab.setSubtitle("sdfsd");
      ab.setBackgroundDrawable(new ColorDrawable(R.drawable.colorPrimaryDrawable));
    }

    @Override
    public void suggestSetupNavigationDrawer(DrawerLayout drawerLayout) {
      drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);

    }
  }
  }

