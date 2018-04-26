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
import com.uber.rib.root.task_act.TaskStatus;
import com.uber.rib.root.task_add_edit.TaskAddEditBuilder;
import com.uber.rib.root.task_add_edit.TaskAddEditRouter;
import com.uber.rib.tutorial1.R;

//import javax.annotation.Nullable;
import android.support.annotation.Nullable;
import android.widget.Toast;

/** Adds and removes children of {@link RootBuilder.RootScope}. */
public class RootRouter extends ViewRouter<RootView, RootInteractor, RootBuilder.Component> {

  private ShowListBuilder showListBuilder;
  @Nullable private ShowListRouter showListRouter;
  private TaskActBuilder taskActBuilder;
  @Nullable private TaskActRouter taskActRouter;
  private TaskAddEditBuilder taskAddEditBuilder;
  @Nullable private TaskAddEditRouter taskAddEditRouter;

  RootRouter(RootView view,
             RootInteractor interactor,
             RootBuilder.Component component,
             TaskActBuilder taskActBuilder,
             ShowListBuilder showListBuilder,
             TaskAddEditBuilder taskAddEditBuilder
             ) {
    super(view, interactor, component);
    this.showListBuilder = showListBuilder;
    this.taskActBuilder = taskActBuilder;
    this.taskAddEditBuilder = taskAddEditBuilder;

  }


  public void attachShowList() {
    this.showListRouter = this.showListBuilder.build(getView());
    attachChild(this.showListRouter);
    getView().addView(this.showListRouter.getView());

  }

  public void detachShowList() {
    if (this.showListRouter != null) {
      detachChild(this.showListRouter);
      getView().removeView(this.showListRouter.getView());
      this.showListRouter = null;
    }
  }

  public TaskActRouter attachTaskAct() {
    if (this.taskActRouter == null) {
      this.taskActRouter = this.taskActBuilder.build(getView());
      attachChild(this.taskActRouter);
      getView().addView(this.taskActRouter.getView());
    }
    return taskActRouter;

  }

  public void detachTaskAct() {
    if (this.taskActRouter != null) {
      detachChild(this.taskActRouter);
      getView().removeView(this.taskActRouter.getView());
      this.taskActRouter = null;
    }
  }

  public void attachTaskAddEdit() {
    if (this.taskAddEditRouter == null) {
      this.taskAddEditRouter = this.taskAddEditBuilder.build(getView());
      attachChild(this.taskAddEditRouter);
      getView().addView(this.taskAddEditRouter.getView());
    }
//    return taskAddEditRouter;

  }

  public void detachTaskAddEdit() {
    if (this.taskAddEditRouter != null) {
      detachChild(this.taskAddEditRouter);
      getView().removeView(this.taskAddEditRouter.getView());
      this.taskAddEditRouter = null;
    }
  }

  public void requestChildRibListenMenuItemSelected(Integer menuItemId) {
    switch (menuItemId) {
      case android.R.id.home: {
        if (taskActRouter != null) {
          taskActRouter.getInteractor().presenter.toggleOpenCloseDrawer();
        }
        break;
      }
      case R.id.menu_clear: {
        if (taskActRouter != null) {

          Toast.makeText(getView().getContext(), "child rib recevied reaquest clear from parent", Toast.LENGTH_SHORT).show();
        }
        break;
      }
      case R.id.menu_filter: {
        Toast.makeText(getView().getContext(), "child rib recevied reaquest open filter from parent", Toast.LENGTH_SHORT).show();

        break;
      }
      case R.id.menu_refresh: {
        Toast.makeText(getView().getContext(), "child rib recevied reaquest refresh from parent", Toast.LENGTH_SHORT).show();

        break;
      }
    }
  }

  public void requestChildRibListenPopupMenuItemSelected(Integer menuItemId) {
    if (taskActRouter != null)
      taskActRouter.getInteractor().getRouter().listenerMenuItemPopupClick(menuItemId);
  }


}
