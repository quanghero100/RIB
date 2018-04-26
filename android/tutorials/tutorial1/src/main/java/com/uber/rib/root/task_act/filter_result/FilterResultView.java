package com.uber.rib.root.task_act.filter_result;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.uber.rib.core.Initializer;
import com.uber.rib.data.Task;
import com.uber.rib.root.task_act.TaskStatus;
import com.uber.rib.tutorial1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Top level view for {@link FilterResultBuilder.FilterResultScope}.
 */
class FilterResultView extends LinearLayout implements FilterResultInteractor.FilterResultPresenter {

  public FilterResultView(Context context) {
    this(context, null);
  }

  public FilterResultView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public FilterResultView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  ListView mListView;
  TaskAdapter mTaskAdapter;
  List<Task> mListTask;

  TaskItemListener mTaskItemListener  = new TaskItemListener() {
    @Override
    public void onTaskClick(Task clickedTask) {
//      mPresenter.openTaskDetails(clickedTask);
    }

    @Override
    public void onCompleteTaskClick(Task completedTask) {
//      mPresenter.completeTask(completedTask);
    }

    @Override
    public void onActivateTaskClick(Task activatedTask) {
//      mPresenter.activateTask(activatedTask);
    }
  };

  @Initializer
  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    // Set up the toolbar.
    mListView = findViewById(R.id.tasks_list);
    mListTask = new ArrayList<Task>();
    mTaskAdapter = new TaskAdapter(mListTask, mTaskItemListener);
    mListView.setAdapter(mTaskAdapter);

  }

  @Override
  public void updateFilterResult(List<Task> tasks) {
//    if (status.equals(TaskStatus.ALL)) {
//      mTaskAdapter.updateData(mListTask);
//    } else if (status.equals(TaskStatus.ACTIVE)) {
//      List<Task> activeTasks = new ArrayList<Task>();
//      for (int i = 0; i < mListTask.size(); i++) {
//        if (mListTask.get(i).isActive()) {
//          activeTasks.add(mListTask.get(i));
//        }
//      }
//
//      mTaskAdapter.updateData(activeTasks);
//
//
//    } else if (status.equals(TaskStatus.COMPLETED)) {
//      List<Task> activeTasks = new ArrayList<Task>();
//      for (int i = 0; i < mListTask.size(); i++) {
//        if (mListTask.get(i).isCompleted()) {
//          activeTasks.add(mListTask.get(i));
//        }
//      }
//
//      mTaskAdapter.updateData(activeTasks);
//    }
      mTaskAdapter.updateData(tasks);

  }

  @Override
  public void loadData(List<Task> listTask) {
//    this.mTaskAdapter.updateData(listTask);
    mListTask = listTask;
  }


  //====================================================================================

  //Adapter for listView
  private static class TaskAdapter extends BaseAdapter {
    private List<Task> mTasks = new ArrayList<Task>();
    private TaskItemListener mItemListener;

    public TaskAdapter(List<Task> tasks, TaskItemListener itemListener) {
      mTasks = tasks;
      mItemListener = itemListener;
    }

    public void updateData(List<Task> tasks) {
      mTasks = tasks;
      this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
      return mTasks.size();
    }

    @Override
    public Task getItem(int i) {
      return mTasks.get(i);
    }

    @Override
    public long getItemId(int i) {
      return mTasks.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
      View rowView = view;
      if (rowView == null) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        rowView = inflater.inflate(R.layout.task_item, viewGroup, false);
      }

      final Task task = (Task) getItem(i);

      TextView titleTV = (TextView) rowView.findViewById(R.id.title);
      titleTV.setText(task.getTitle());

      CheckBox completeCB = (CheckBox) rowView.findViewById(R.id.complete);

      // Active/completed task UI
      completeCB.setChecked(task.isCompleted());
      if (task.isCompleted()) {
        rowView.setBackgroundDrawable(viewGroup.getContext()
                .getResources().getDrawable(R.drawable.list_completed_touch_feedback));
      } else {
        rowView.setBackgroundDrawable(viewGroup.getContext()
                .getResources().getDrawable(R.drawable.touch_feedback));
      }

      completeCB.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if (!task.isCompleted()) {
            mItemListener.onCompleteTaskClick(task);
          } else {
            mItemListener.onActivateTaskClick(task);
          }
        }
      });

      rowView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          mItemListener.onTaskClick(task);
        }
      });

      return rowView;
    }
  }

  //Interface task item listener

  public interface TaskItemListener {

    void onTaskClick(Task clickedTask);

    void onCompleteTaskClick(Task completedTask);

    void onActivateTaskClick(Task activatedTask);
  }
}
