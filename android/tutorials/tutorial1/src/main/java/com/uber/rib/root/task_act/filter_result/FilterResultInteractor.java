package com.uber.rib.root.task_act.filter_result;

import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.uber.rib.core.Bundle;
import com.uber.rib.core.Interactor;
import com.uber.rib.core.RibInteractor;
import com.uber.rib.core.Presenter;
import com.uber.rib.core.Router;
import com.uber.rib.data.Task;
import com.uber.rib.root.task_act.TaskStatus;
import com.uber.rib.tutorial1.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Coordinates Business Logic for {@link FilterResultBuilder.FilterResultScope}.
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
public class FilterResultInteractor
    extends Interactor<FilterResultInteractor.FilterResultPresenter, FilterResultRouter> {

  @Inject public FilterResultPresenter presenter;
  @Inject Listener listener;
//  public Integer mCurrentStatusFilterResult = TaskStatus.ALL;


  @Override
  protected void didBecomeActive(@Nullable Bundle savedInstanceState) {
    super.didBecomeActive(savedInstanceState);

    // TODO: Add attachment logic here (RxSubscriptions, etc.).
//    presenter.itemClicks()
//            .subscribe(new Consumer<Pair<Integer, Task>>() {
//              @Override
//              public void accept(Pair<Integer, Task> integerTaskPair) throws Exception {
//                Toast.makeText(getRouter().getView().getContext(), integerTaskPair.second.getTitle(), Toast.LENGTH_SHORT).show();
//              }
//            });

  }

  @Override
  protected void willResignActive() {
    super.willResignActive();

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  public void showFilterResult(List<Task> tasks) {

    presenter.updateFilterResult(tasks);

  }


  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface FilterResultPresenter {
    void updateFilterResult(List<Task> tasks);
    List<Task> addNewTask(Task task);
    void completeTask(Task task);
    void activeTask(Task task);
  }


  public interface Listener {
    void requestShowTaskDetail(Task task);
  }

}
