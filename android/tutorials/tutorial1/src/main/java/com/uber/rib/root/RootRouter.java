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
import com.uber.rib.data.Task;
import com.uber.rib.root.task_act.TaskActBuilder;
import com.uber.rib.root.task_act.TaskActRouter;
import com.uber.rib.root.task_add.TaskAddBuilder;
import com.uber.rib.root.task_add.TaskAddRouter;
import com.uber.rib.root.task_detail.TaskDetailBuilder;
import com.uber.rib.root.task_detail.TaskDetailRouter;
import com.uber.rib.tutorial1.R;

//import javax.annotation.Nullable;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.ArrayList;

/** Adds and removes children of {@link RootBuilder.RootScope}. */
public class RootRouter extends ViewRouter<RootView, RootInteractor, RootBuilder.Component> {

//  private ShowListBuilder showListBuilder;
//  @Nullable private ShowListRouter showListRouter;
  private TaskActBuilder taskActBuilder;
  @Nullable public TaskActRouter taskActRouter;
  private TaskAddBuilder taskAddBuilder;
  @Nullable private TaskAddRouter taskAddRouter;
  private TaskDetailBuilder taskDetailBuilder;
  @Nullable public TaskDetailRouter taskDetailRouter;

  public ArrayList<ViewRouter> listRouter = new ArrayList<>();

  RootRouter(RootView view,
             RootInteractor interactor,
             RootBuilder.Component component,
             TaskActBuilder taskActBuilder,
             TaskAddBuilder taskAddBuilder,
             TaskDetailBuilder taskDetailBuilder
             ) {
    super(view, interactor, component);
//    this.showListBuilder = showListBuilder;
    this.taskActBuilder = taskActBuilder;
    this.taskAddBuilder = taskAddBuilder;
    this.taskDetailBuilder = taskDetailBuilder;

  }


//  public void attachShowList() {
//    this.showListRouter = this.showListBuilder.build(getView());
//    attachChild(this.showListRouter);
//    getView().addView(this.showListRouter.getView());
//
//  }

//  public void detachShowList() {
//    if (this.showListRouter != null) {
//      detachChild(this.showListRouter);
//      getView().removeView(this.showListRouter.getView());
//      this.showListRouter = null;
//    }
//  }

  public TaskActRouter attachTaskAct() {
    if (this.taskActRouter == null) {
      this.taskActRouter = this.taskActBuilder.build(getView());
      listRouter.add(taskActRouter);
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
      listRouter.remove(listRouter.size() - 1);
    }
  }

  public void attachTaskAdd() {
    if (this.taskAddRouter == null) {
      this.taskAddRouter = this.taskAddBuilder.build(getView());
      listRouter.add(taskAddRouter);
      attachChild(this.taskAddRouter);
      getView().addView(this.taskAddRouter.getView());

    }
  }

  public void detachTaskAdd() {
    if (this.taskAddRouter != null) {
      detachChild(this.taskAddRouter);
      getView().removeView(this.taskAddRouter.getView());
      this.taskAddRouter = null;
      listRouter.remove(listRouter.size() - 1);

    }
  }

  public void attachTaskDetail() {
    if (this.taskDetailRouter == null) {
      this.taskDetailRouter = this.taskDetailBuilder.build(getView());
      listRouter.add(taskDetailRouter);
      attachChild(this.taskDetailRouter);
      getView().addView(this.taskDetailRouter.getView());

    }
  }

  public void detachTaskDetail() {
    if (this.taskDetailRouter != null) {
      detachChild(this.taskDetailRouter);
      getView().removeView(this.taskDetailRouter.getView());
      this.taskDetailRouter = null;
      listRouter.remove(listRouter.size() - 1);

    }
  }

  public void requestChildRibListenMenuItemSelected(Integer menuItemId) {
    switch (menuItemId) {
      case android.R.id.home: {

        if (taskActRouter != null && listRouter.size() == 1) {
          taskActRouter.getInteractor().presenter.toggleOpenCloseDrawer();

        }

        if (taskAddRouter != null && listRouter.size() > 1) {
          detachTaskAdd();
          getInteractor().listener.suggestSetupActionBar(R.string.app_name, true);


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

  public boolean requestChildRibListenBackPress() {
    if (listRouter.size() > 1) {
      detachTaskAdd();
      getInteractor().listener.suggestSetupActionBar(R.string.app_name, true);
      return false;
    }
    return true;

  }

  public void requestAddNewTask(Task task) {
    if (taskActRouter != null) {
      taskActRouter.requestAddNewTask(task);

    }
  }


}
