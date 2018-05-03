package com.uber.rib.root.task_act;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import com.jakewharton.rxbinding2.view.RxMenuItem;
import com.jakewharton.rxbinding2.view.RxView;
import com.uber.rib.RootActivity;
import com.uber.rib.core.Initializer;
import com.uber.rib.core.RibActivity;
import com.uber.rib.data.Task;
import com.uber.rib.tutorial1.R;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Top level view for {@link TaskActBuilder.TaskActScope}.
 */
public class TaskActView extends DrawerLayout implements TaskActInteractor.TaskActPresenter {

  public DrawerLayout getDrawerLayout() {
    return mDrawerLayout;
  }

  private DrawerLayout mDrawerLayout;

  public Toolbar getToolbar() {
    return mToolbar;
  }

  private Toolbar mToolbar;


  public RootActivity mActivity = new RootActivity();

  private FloatingActionButton fabAddTask;

  public void setActivity(RootActivity activity) {
    if (activity != null)
      mActivity = activity;
  }


  public TaskActView(Context context) {
    this(context, null);
  }

  public TaskActView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public TaskActView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Initializer
  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    // Set up the toolbar.
    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    mDrawerLayout = findViewById(R.id.drawer_layout);
    fabAddTask = findViewById(R.id.fab_add_task);

  }

  @Override
  public void toggleOpenCloseDrawer() {
    if (mDrawerLayout != null) {
//        boolean check = mDrawerLayout.isDrawerOpen(Gravity.START);
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
          mDrawerLayout.closeDrawer(Gravity.START);
        } else {
          mDrawerLayout.openDrawer(Gravity.START);
        }
    }
  }

  @Override
  public void closeDrawer() {
    mDrawerLayout.closeDrawers();
  }

  @Override
  public Observable<Boolean> addTaskButtonClick() {

    return RxView.clicks(fabAddTask)
            .map(new Function<Object, Boolean>() {
              @Override
              public Boolean apply(Object o) throws Exception {
                return true;
              }
            });
  }

  @Override
  public void showAddTaskSuccess() {
    Snackbar.make(fabAddTask, R.string.successfully_saved_task_message, Snackbar.LENGTH_SHORT).show();

  }


}
