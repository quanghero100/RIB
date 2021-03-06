package com.uber.rib.root.task_act;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.FrameLayout;

import com.uber.rib.core.ViewRouter;
import com.uber.rib.data.Task;
import com.uber.rib.root.common.navigation_drawer.NavigationDrawerBuilder;
import com.uber.rib.root.common.navigation_drawer.NavigationDrawerRouter;
import com.uber.rib.root.task_act.filter_empty.FilterEmptyBuilder;
import com.uber.rib.root.task_act.filter_empty.FilterEmptyRouter;
import com.uber.rib.root.task_act.filter_result.FilterResultBuilder;
import com.uber.rib.root.task_act.filter_result.FilterResultRouter;
import com.uber.rib.tutorial1.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Adds and removes children of {@link TaskActBuilder.TaskActScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
public class TaskActRouter extends
    ViewRouter<TaskActView, TaskActInteractor, TaskActBuilder.Component> {

  @Nullable
  private NavigationDrawerBuilder navigationDrawerBuilder;
  @Nullable
  private NavigationDrawerRouter navigationDrawerRouter;

  @Nullable
  private FilterEmptyBuilder filterEmptyBuilder;
  @Nullable
  private FilterEmptyRouter filterEmptyRouter;

  @Nullable
  private FilterResultBuilder filterResultBuilder;
  @Nullable
  private FilterResultRouter filterResultRouter;

  private Integer mCurrentMenuItemId = R.id.all;

  List<Task> mListTask = new ArrayList<>();

  public TaskActRouter(
      TaskActView view,
      TaskActInteractor interactor,
      TaskActBuilder.Component component,
      NavigationDrawerBuilder navigationDrawerBuilder,
      FilterEmptyBuilder filterEmptyBuilder,
      FilterResultBuilder filterResultBuilder
  ) {
    super(view, interactor, component);
    this.navigationDrawerBuilder = navigationDrawerBuilder;
    this.filterEmptyBuilder = filterEmptyBuilder;
    this.filterResultBuilder = filterResultBuilder;


  }

  public void requestAddNewTask(Task task) {
    if (filterResultRouter != null) {
      mListTask = filterResultRouter.requestAddNewTask(task);

    } else if (filterResultRouter == null) {
      detachFilterEmpty();
      attachFilterResult();
      if (filterResultRouter != null) {
        filterResultRouter.requestUpdateData(mListTask);
        mListTask = filterResultRouter.requestAddNewTask(task);
        getInteractor().setListTask(mListTask);
        listenerMenuItemPopupClick(mCurrentMenuItemId);
      }

    }
    getInteractor().presenter.showAddTaskSuccess();
  }

  public void listenerMenuItemPopupClick(Integer menuItemId) {
    mListTask = getInteractor().getListTask();
    mCurrentMenuItemId = menuItemId;
    List<Task> tasks = new ArrayList<>();
    Integer statusFilter = getInteractor().getStatusFiler();
    switch (menuItemId) {
      case R.id.all: {
        statusFilter = TaskStatus.ALL;
        tasks = mListTask;
        break;
      }
      case R.id.active: {
        statusFilter = TaskStatus.ACTIVE;
        for (int i = 0; i < mListTask.size(); i++) {
          if (mListTask.get(i).isActive()) {
            tasks.add(mListTask.get(i));
          }
        }

        break;
      }
      case R.id.completed: {
        statusFilter = TaskStatus.COMPLETED;
        for (int i = 0; i < mListTask.size(); i++) {
          if (mListTask.get(i).isCompleted()) {
            tasks.add(mListTask.get(i));
          }
        }

        break;
      }
    }


    if (tasks.size() > 0 && filterResultRouter != null) {
      filterResultRouter.getInteractor().showFilterResult(tasks);
    } else if (tasks.size() > 0) {
      detachFilterEmpty();
      attachFilterResult();
      if (filterResultRouter != null) {
        filterResultRouter.getInteractor().showFilterResult(tasks);
      }
    } else if (filterEmptyRouter != null){

      filterEmptyRouter.getInteractor().showFilterEmpty(statusFilter);
    } else if (filterEmptyRouter == null) {
      detachFilterResult();
      attachFilterEmpty();
      if (filterEmptyRouter != null) {
        filterEmptyRouter.getInteractor().showFilterEmpty(statusFilter);
      }
    }

  }


  public void attachNavigationDrawer() {
    if (this.navigationDrawerBuilder != null) {
      View parentView = getView();

      this.navigationDrawerRouter = this.navigationDrawerBuilder.build(getView());
      attachChild(this.navigationDrawerRouter);
      getView().addView(this.navigationDrawerRouter.getView());
    }
  }

  public void detachNavigationDrawer() {
    if (this.navigationDrawerRouter != null) {
      detachChild(this.navigationDrawerRouter);
      getView().removeView(this.navigationDrawerRouter.getView());
      this.navigationDrawerRouter = null;
    }
  }



  public void attachFilterEmpty() {
    if (this.filterEmptyBuilder != null) {
      View parentView = getView();
      View view = getView();
      FrameLayout frameLayout = view.findViewById(R.id.contentFrame);
      this.filterEmptyRouter = this.filterEmptyBuilder.build(frameLayout);
      attachChild((this.filterEmptyRouter));

      frameLayout.addView(this.filterEmptyRouter.getView());
    }
  }

  public void detachFilterEmpty() {
    if (this.filterEmptyRouter != null) {
      detachChild(this.filterEmptyRouter);
      View view = getView();
      FrameLayout frameLayout = view.findViewById(R.id.contentFrame);
      frameLayout.removeView(this.filterEmptyRouter.getView());
      this.filterEmptyRouter = null;
    }
  }

  public void attachFilterResult() {
    if (this.filterResultBuilder != null) {
      View parentView = getView();
      View view = getView();
      FrameLayout frameLayout = view.findViewById(R.id.contentFrame);
      this.filterResultRouter = this.filterResultBuilder.build(frameLayout);
      attachChild((this.filterResultRouter));

      frameLayout.addView(this.filterResultRouter.getView());
    }
  }

  public void detachFilterResult() {
    if (this.filterResultRouter != null) {
      detachChild(this.filterResultRouter);
      View view = getView();
      FrameLayout frameLayout = view.findViewById(R.id.contentFrame);
      frameLayout.removeView(this.filterResultRouter.getView());
      this.filterResultRouter = null;
    }
  }
}
