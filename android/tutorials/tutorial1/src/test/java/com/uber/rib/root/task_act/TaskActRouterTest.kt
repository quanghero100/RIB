package com.uber.rib.root.task_act

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TaskActRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: TaskActBuilder.Component
  @Mock internal lateinit var interactor: TaskActInteractor
  @Mock internal lateinit var view: TaskActView

  private var router: TaskActRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = TaskActRouter(view, interactor, component)
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

