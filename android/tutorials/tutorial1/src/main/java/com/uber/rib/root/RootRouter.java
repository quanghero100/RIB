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

import com.uber.rib.core.ViewRouter;
import com.uber.rib.root.common.navigation_drawer.NavigationDrawerBuilder;
import com.uber.rib.root.common.navigation_drawer.NavigationDrawerRouter;
import com.uber.rib.root.show_list.ShowListBuilder;
import com.uber.rib.root.show_list.ShowListRouter;
import com.uber.rib.root.task_act.TaskActBuilder;
import com.uber.rib.root.task_act.TaskActRouter;

//import javax.annotation.Nullable;
import android.support.annotation.Nullable;

/** Adds and removes children of {@link RootBuilder.RootScope}. */
public class RootRouter extends ViewRouter<RootView, RootInteractor, RootBuilder.Component> {

//  private ShowListBuilder showListBuilder;
//  @Nullable private ShowListRouter showListRouter;
  private TaskActBuilder taskActBuilder;
  @Nullable private TaskActRouter taskActRouter;


  RootRouter(RootView view,
             RootInteractor interactor,
             RootBuilder.Component component,
//             ShowListBuilder showListBuilder
             TaskActBuilder taskActBuilder
             ) {
    super(view, interactor, component);
//    this.showListBuilder = showListBuilder;
    this.taskActBuilder = taskActBuilder;

  }


//  public void attachShowList() {
//    this.showListRouter = this.showListBuilder.build(getView());
//    attachChild(this.showListRouter);
//    getView().addView(this.showListRouter.getView());
//  }
//
//  public void detachShowList() {
//    if (this.showListRouter != null) {
//      detachChild(this.showListRouter);
//      getView().removeView(this.showListRouter.getView());
//      this.showListRouter = null;
//    }
//  }

  public TaskActRouter attachTaskAct() {
    this.taskActRouter = this.taskActBuilder.build(getView());
    attachChild(this.taskActRouter);
    getView().addView(this.taskActRouter.getView());
    return taskActRouter;
  }

  public void detachTaskAct() {
    if (this.taskActRouter != null) {
      detachChild(this.taskActRouter);
      getView().removeView(this.taskActRouter.getView());
      this.taskActRouter = null;
    }
  }



}
