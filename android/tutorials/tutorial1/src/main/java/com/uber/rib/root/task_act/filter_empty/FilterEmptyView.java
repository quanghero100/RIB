package com.uber.rib.root.task_act.filter_empty;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uber.rib.core.Initializer;
import com.uber.rib.root.task_act.TaskStatus;
import com.uber.rib.tutorial1.R;

/**
 * Top level view for {@link FilterEmptyBuilder.FilterEmptyScope}.
 */
class FilterEmptyView extends LinearLayout implements FilterEmptyInteractor.FilterEmptyPresenter {

  public FilterEmptyView(Context context) {
    this(context, null);
  }

  public FilterEmptyView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public FilterEmptyView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  ImageView noTaskIcon;
  TextView noTasksMain;

  @Initializer
  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    // Set up the toolbar.

    noTaskIcon = findViewById(R.id.noTasksIcon);
    noTasksMain = findViewById(R.id.noTasksMain);


  }

  @Override
  public void setFilterEmptyMatchingWithStatus(Integer status) {
    if (status.equals(TaskStatus.ALL)) {
      noTaskIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_assignment_turned_in_24dp));
      noTasksMain.setText(getResources().getString(R.string.label_all));
    } else if (status.equals(TaskStatus.ACTIVE)) {
      noTaskIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_circle_24dp));
      noTasksMain.setText(getResources().getString(R.string.label_active));
    } else if (status.equals(TaskStatus.COMPLETED)) {
      noTaskIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_verified_user_24dp));
      noTasksMain.setText(getResources().getString(R.string.label_completed));
    }
  }
}
