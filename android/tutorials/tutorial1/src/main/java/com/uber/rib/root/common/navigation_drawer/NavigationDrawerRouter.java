package com.uber.rib.root.common.navigation_drawer;

import android.support.annotation.NonNull;
import android.view.View;

import com.uber.rib.core.ViewRouter;

/**
 * Adds and removes children of {@link NavigationDrawerBuilder.NavigationDrawerScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
public class NavigationDrawerRouter extends
    ViewRouter<NavigationDrawerView, NavigationDrawerInteractor, NavigationDrawerBuilder.Component> {

  public NavigationDrawerRouter(
      NavigationDrawerView view,
      NavigationDrawerInteractor interactor,
      NavigationDrawerBuilder.Component component) {
    super(view, interactor, component);
  }
}
