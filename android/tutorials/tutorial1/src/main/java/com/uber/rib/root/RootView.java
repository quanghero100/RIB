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

package com.uber.rib.root;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.uber.rib.RootActivity;
import com.uber.rib.core.RibActivity;
import com.uber.rib.tutorial1.R;

/** Top level view for {@link RootBuilder.RootScope}. */
public class RootView extends FrameLayout implements RootInteractor.RootPresenter {

  public void setActivity(RibActivity mActivity) {
    if (mActivity != null)
      this.mActivity = mActivity;
  }

  @Nullable RibActivity mActivity;
  public RootView(Context context) {
    this(context, null);
  }

  public RootView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public RootView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }


  @Override
  public void setSupportActionBar(Toolbar toolbar) {
//    RootActivity.setSupportActionBar(toolbar);
    if (mActivity != null) {
//      mActivity.setSupportActionBar(toolbar);
//      ActionBar ab = mActivity.getSupportActionBar();
//      ab.setHomeAsUpIndicator(R.drawable.ic_menu);
//      ab.setDisplayHomeAsUpEnabled(true);
    }
  }
}
