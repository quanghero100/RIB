package com.uber.rib.root.task_add_edit;

import android.support.annotation.NonNull;
import android.view.View;

import com.uber.rib.core.ViewRouter;

/**
 * Adds and removes children of {@link TaskAddEditBuilder.TaskAddEditScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
public class TaskAddEditRouter extends
    ViewRouter<TaskAddEditView, TaskAddEditInteractor, TaskAddEditBuilder.Component> {

  public TaskAddEditRouter(
      TaskAddEditView view,
      TaskAddEditInteractor interactor,
      TaskAddEditBuilder.Component component) {
    super(view, interactor, component);
  }
}
