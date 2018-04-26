package com.uber.rib.root.task_act.filter_empty;

import android.support.annotation.Nullable;

import com.uber.rib.core.Bundle;
import com.uber.rib.core.Interactor;
import com.uber.rib.core.RibInteractor;
import com.uber.rib.core.Presenter;
import com.uber.rib.core.Router;
import com.uber.rib.root.task_act.TaskStatus;
import com.uber.rib.tutorial1.R;

import javax.inject.Inject;

/**
 * Coordinates Business Logic for {@link FilterEmptyScope}.
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
public class FilterEmptyInteractor
    extends Interactor<FilterEmptyInteractor.FilterEmptyPresenter, FilterEmptyRouter> {

  @Inject FilterEmptyPresenter presenter;
  public Integer mCurrentStatusFilterEmpty = TaskStatus.ALL;

  @Override
  protected void didBecomeActive(@Nullable Bundle savedInstanceState) {
    super.didBecomeActive(savedInstanceState);

    // TODO: Add attachment logic here (RxSubscriptions, etc.).
    presenter.setFilterEmptyMatchingWithStatus(mCurrentStatusFilterEmpty);
  }

  @Override
  protected void willResignActive() {
    super.willResignActive();

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }


  public void showFilterEmpty(Integer statusFilter) {
    presenter.setFilterEmptyMatchingWithStatus(statusFilter);
  }
  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface FilterEmptyPresenter {
    void setFilterEmptyMatchingWithStatus(Integer status);
  }
}
