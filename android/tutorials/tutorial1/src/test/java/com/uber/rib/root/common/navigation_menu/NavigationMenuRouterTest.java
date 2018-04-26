package com.uber.rib.root.common.navigation_menu;

import com.uber.rib.core.RibTestBasePlaceholder;
import com.uber.rib.core.RouterHelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class NavigationMenuRouterTest extends RibTestBasePlaceholder {

  @Mock NavigationMenuBuilder.Component component;
  @Mock NavigationMenuInteractor interactor;
  @Mock NavigationMenuView view;

  private NavigationMenuRouter router;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    router = new NavigationMenuRouter(view, interactor, component);
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  public void anExampleTest_withSomeConditions_shouldPass() {
    // Use RouterHelper to drive your router's lifecycle.
    RouterHelper.attach(router);
    RouterHelper.detach(router);

    throw new RuntimeException("Remove this test and add real tests.");
  }

}
