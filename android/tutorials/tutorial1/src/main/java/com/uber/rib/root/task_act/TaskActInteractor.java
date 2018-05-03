package com.uber.rib.root.task_act;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.uber.rib.core.Bundle;
import com.uber.rib.core.Interactor;
import com.uber.rib.core.RibInteractor;
import com.uber.rib.core.Presenter;
import com.uber.rib.core.Router;
import com.uber.rib.data.Task;
import com.uber.rib.root.common.navigation_drawer.NavigationDrawerInteractor;
import com.uber.rib.root.task_act.filter_result.FilterResultInteractor;
import com.uber.rib.tutorial1.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Coordinates Business Logic for {@link TaskActBuilder.TaskActScope}.
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
public class TaskActInteractor
    extends Interactor<TaskActInteractor.TaskActPresenter, TaskActRouter> {

  @Inject public TaskActPresenter presenter;
  @Inject Listener listener;

  public Integer getStatusData() {
    return mStatusData;
  }

  public void setStatusData(Integer mStatusData) {
    this.mStatusData = mStatusData;
  }

  Integer mStatusData = TaskStatus.NO_DATA;

  public Integer getStatusFiler() {
    return mStatusFiler;
  }

  public void setStatusFiler(Integer mStatusFiler) {
    this.mStatusFiler = mStatusFiler;
  }

  Integer mStatusFiler = TaskStatus.ALL;

  public List<Task> getListTask() {
    return mListTask;
  }

  public void setListTask(List<Task> mListTask) {
    this.mListTask = mListTask;
  }

  public List<Task> addToListTask(Task task) {
    mListTask.add(task);
    return mListTask;
  }

  public List<Task> deleteTaskAt(int index) {
    mListTask.remove(index);
    return mListTask;
  }

  public List<Task> deleteAllTaskCompleted() {
    for (int i = mListTask.size() - 1; i >= 0; i--) {
      if (mListTask.get(i).isCompleted()) {
        mListTask.remove(i);
      }
    }
    return mListTask;
  }

  List<Task> mListTask = new ArrayList<>();

  @Override
  protected void didBecomeActive(@Nullable Bundle savedInstanceState) {
    super.didBecomeActive(savedInstanceState);

    // TODO: Add attachment logic here (RxSubscriptions, etc.).

    getRouter().attachNavigationDrawer();
    listener.requestAddMenuTaskFragment(R.menu.tasks_fragment_menu);
//    mListTask = loadData();
    mStatusData = mListTask.size() > 0 ? TaskStatus.HAVE_DATA : TaskStatus.NO_DATA;

    if (mStatusData.equals(TaskStatus.NO_DATA)) {
        getRouter().attachFilterEmpty();
    } else if (mStatusData.equals(TaskStatus.HAVE_DATA)) {
        getRouter().attachFilterResult();
    }

    getRouter().listenerMenuItemPopupClick(R.id.all);

    presenter.addTaskButtonClick()
            .subscribe(new Consumer<Boolean>() {
              @Override
              public void accept(Boolean aBoolean) throws Exception {
                listener.requestAttachAddTaskView();
              }
            });


  }



  @Override
  protected void willResignActive() {
    super.willResignActive();

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  class NavigationDrawerListener implements NavigationDrawerInteractor.Listener {

    @Override
    public void requestMenuItemListClick(Integer menuItemId) {
      presenter.closeDrawer();
      listener.requestMenuItemListClick(menuItemId);

    }

    @Override
    public void requestMenuItemStatisticClick(Integer menuItemId) {
      presenter.closeDrawer();
      listener.requestMenuItemStatisticClick(menuItemId);
    }
  }

  class FilterResultListener implements FilterResultInteractor.Listener {

    @Override
    public void requestShowTaskDetail(Task task) {
      listener.requestAttachTaskDetailView(task);
    }
  }

//
//  List<Task> loadData() {
//
//    Task[] tasks = {
////            new Task((long) 0, "dfdfdf", "fjsdf", false),
////            new Task((long) 1, "dfdfdf", "fjsdf", true),
////            new Task((long) 2, "dfdfdf", "fjsdf", true),
////            new Task((long) 3, "dfdfdf", "fjsdf", false),
////            new Task((long) 4, "dfdfdf", "fjsdf", true),
////            new Task((long) 5, "dfdfdf", "fjsdf", true),
////            new Task((long) 6, "dfdfdf", "fjsdf", false),
//    };
//
//    return new ArrayList<Task>(Arrays.asList(tasks));
//  }


  /**
   * Presenter interface implemented by this RIB's view.
   */
  public interface TaskActPresenter {
    void toggleOpenCloseDrawer();
    void closeDrawer();
    Observable<Boolean> addTaskButtonClick();
    void  showAddTaskSuccess();

//    void updateStatusData();
  }

  public interface Listener {
    void requestMenuItemListClick(Integer menuItemId);
    void requestMenuItemStatisticClick(Integer menuItemId);
    void requestAddMenuTaskFragment(Integer resourceMenuId);
    void requestAttachAddTaskView();
    void requestAttachTaskDetailView(Task task);
  }
}
