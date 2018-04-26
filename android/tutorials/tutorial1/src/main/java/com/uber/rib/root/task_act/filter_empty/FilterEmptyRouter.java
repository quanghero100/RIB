package com.uber.rib.root.task_act.filter_empty;

import android.support.annotation.NonNull;
import android.view.View;

import com.uber.rib.core.ViewRouter;

/**
 * Adds and removes children of {@link FilterEmptyBuilder.FilterEmptyScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
public class FilterEmptyRouter extends
    ViewRouter<FilterEmptyView, FilterEmptyInteractor, FilterEmptyBuilder.Component> {

  public FilterEmptyRouter(
      FilterEmptyView view,
      FilterEmptyInteractor interactor,
      FilterEmptyBuilder.Component component) {
    super(view, interactor, component);
  }
}
