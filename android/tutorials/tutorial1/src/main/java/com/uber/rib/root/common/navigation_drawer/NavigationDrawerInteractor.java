package com.uber.rib.root.common.navigation_drawer;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.uber.rib.core.Bundle;
import com.uber.rib.core.Interactor;
import com.uber.rib.core.RibInteractor;
import com.uber.rib.tutorial1.R;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Coordinates Business Logic for {@link NavigationDrawerBuilder.NavigationDrawerScope}.
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
public class NavigationDrawerInteractor
    extends Interactor<NavigationDrawerInteractor.NavigationDrawerPresenter, NavigationDrawerRouter> {

  @Inject NavigationDrawerPresenter presenter;
  @Inject
  Listener listener;


  @Nullable
  Menu mMenu;
  @Override
  protected void didBecomeActive(@Nullable Bundle savedInstanceState) {
    super.didBecomeActive(savedInstanceState);

    // TODO: Add attachment logic here (RxSubscriptions, etc.).
    presenter.menuItemListClick()
            .subscribe(new Consumer<Integer>() {
              @Override
              public void accept(@Nullable Integer integer) throws Exception {
                if (integer != null) {
                    listener.requestMenuItemListClick(integer);
                }
              }
            });
    presenter.menuItemStatisticClick()
            .subscribe(new Consumer<Integer>() {
              @Override
              public void accept(@Nullable Integer integer) throws Exception {
                if (integer != null) {
                    listener.requestMenuItemStatisticClick(integer);
                }
              }
            });
//    presenter.menuItemListClick();
//    presenter.menuItemStatisticClick();

  }

  @Override
  protected void willResignActive() {
    super.willResignActive();

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.




  }


  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface NavigationDrawerPresenter {
    Observable<Integer> menuItemListClick();
    Observable<Integer> menuItemStatisticClick();

  }

  public interface Listener {
    void requestMenuItemListClick(Integer menuItemId);
    void requestMenuItemStatisticClick(Integer menuItemId);
  }
}
