package com.uber.rib.root.show_list;

import android.support.annotation.NonNull;
import android.view.View;

import com.uber.rib.core.ViewRouter;

/**
 * Adds and removes children of {@link ShowListBuilder.ShowListScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
public class ShowListRouter extends
    ViewRouter<ShowListView, ShowListInteractor, ShowListBuilder.Component> {

  public ShowListRouter(
      ShowListView view,
      ShowListInteractor interactor,
      ShowListBuilder.Component component) {
    super(view, interactor, component);
  }
}
