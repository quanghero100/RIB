package com.uber.rib.root.task_add_edit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Top level view for {@link TaskAddEditBuilder.TaskAddEditScope}.
 */
class TaskAddEditView extends LinearLayout implements TaskAddEditInteractor.TaskAddEditPresenter {

  public TaskAddEditView(Context context) {
    this(context, null);
  }

  public TaskAddEditView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public TaskAddEditView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }
}
