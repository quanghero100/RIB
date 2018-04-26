package com.uber.rib.root.common.navigation_drawer

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.InteractorHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NavigationDrawerInteractorTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var presenter: NavigationDrawerInteractor.NavigationDrawerPresenter
  @Mock internal lateinit var router: NavigationDrawerRouter

  private var interactor: NavigationDrawerInteractor? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    interactor = TestNavigationDrawerInteractor.create(presenter)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach<NavigationDrawerInteractor.NavigationDrawerPresenter, NavigationDrawerRouter>(interactor!!, presenter, router, null)
    InteractorHelper.detach(interactor!!)

    throw RuntimeException("Remove this test and add real tests.")
  }
}