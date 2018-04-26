package com.uber.rib.root.task_act.filter_result;

import android.support.annotation.NonNull;
import android.view.View;

import com.uber.rib.core.ViewRouter;

/**
 * Adds and removes children of {@link FilterResultBuilder.FilterResultScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
public class FilterResultRouter extends
    ViewRouter<FilterResultView, FilterResultInteractor, FilterResultBuilder.Component> {

  public FilterResultRouter(
      FilterResultView view,
      FilterResultInteractor interactor,
      FilterResultBuilder.Component component) {
    super(view, interactor, component);
  }
}
