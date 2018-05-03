package com.uber.rib.root.task_act;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.uber.rib.RootActivity;
import com.uber.rib.core.InteractorBaseComponent;
import com.uber.rib.core.ViewBuilder;
import com.uber.rib.root.common.navigation_drawer.NavigationDrawerBuilder;
import com.uber.rib.root.common.navigation_drawer.NavigationDrawerInteractor;
import com.uber.rib.root.task_act.filter_empty.FilterEmptyBuilder;
import com.uber.rib.root.task_act.filter_result.FilterResultBuilder;
import com.uber.rib.root.task_act.filter_result.FilterResultInteractor;
import com.uber.rib.tutorial1.R;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;
import javax.inject.Scope;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Provides;

import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Builder for the {@link TaskActScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
public class TaskActBuilder
    extends ViewBuilder<TaskActView, TaskActRouter, TaskActBuilder.ParentComponent> {


  RootActivity mActivity = new RootActivity();

  public TaskActBuilder(ParentComponent dependency, RootActivity activity) {
    super(dependency);
    if (activity != null)
      mActivity = activity;

  }

  /**
   * Builds a new {@link TaskActRouter}.
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new {@link TaskActRouter}.
   */
  public TaskActRouter build(ViewGroup parentViewGroup) {
    TaskActView view = createView(parentViewGroup);
    TaskActInteractor interactor = new TaskActInteractor();
    Component component = DaggerTaskActBuilder_Component.builder()
        .parentComponent(getDependency())
        .view(view)
        .interactor(interactor)
        .build();
    return component.taskactRouter();
  }

  @Override
  protected TaskActView inflateView(LayoutInflater inflater, ViewGroup parentViewGroup) {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    TaskActView taskActView = (TaskActView) inflater.inflate(R.layout.task_act, parentViewGroup, false);
    taskActView.setActivity(mActivity);
    return  taskActView;
  }

  public interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
    TaskActInteractor.Listener listener();
  }

  @dagger.Module
  public abstract static class Module {

    @TaskActScope
    @Binds
    abstract TaskActInteractor.TaskActPresenter presenter(TaskActView view);

    @TaskActScope
    @Provides
    static TaskActRouter router(
      Component component,
      TaskActView view,
      TaskActInteractor interactor) {
      return new TaskActRouter(view,
              interactor,
              component,
              new NavigationDrawerBuilder(component),
              new FilterEmptyBuilder(component),
              new FilterResultBuilder(component)
      );
    }

    @TaskActScope
    @Provides
    static NavigationDrawerInteractor.Listener navigationDrawerListener(TaskActInteractor interactor) {
      return interactor. new NavigationDrawerListener();
    }

    @TaskActScope
    @Provides
    static FilterResultInteractor.Listener filterResultListener(TaskActInteractor interactor) {
      return interactor. new FilterResultListener();
    }
    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @TaskActScope
  @dagger.Component(modules = Module.class,
       dependencies = ParentComponent.class)
  interface Component extends InteractorBaseComponent<TaskActInteractor>,
          BuilderComponent,
          NavigationDrawerBuilder.ParentComponent,
          FilterEmptyBuilder.ParentComponent,
          FilterResultBuilder.ParentComponent
  {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      Builder interactor(TaskActInteractor interactor);
      @BindsInstance
      Builder view(TaskActView view);
      Builder parentComponent(ParentComponent component);
      Component build();
    }
  }

  interface BuilderComponent  {
    TaskActRouter taskactRouter();
  }

  @Scope
  @Retention(CLASS)
  @interface TaskActScope { }

  @Qualifier
  @Retention(CLASS)
  @interface TaskActInternal { }
}
