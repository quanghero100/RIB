package com.uber.rib.root.common.navigation_drawer

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NavigationDrawerRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: NavigationDrawerBuilder.Component
  @Mock internal lateinit var interactor: NavigationDrawerInteractor
  @Mock internal lateinit var view: NavigationDrawerView

  private var router: NavigationDrawerRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = NavigationDrawerRouter(view, interactor, component)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use RouterHelper to drive your router's lifecycle.
    RouterHelper.attach(router!!)
    RouterHelper.detach(router!!)

    throw RuntimeException("Remove this test and add real tests.")
  }

}

