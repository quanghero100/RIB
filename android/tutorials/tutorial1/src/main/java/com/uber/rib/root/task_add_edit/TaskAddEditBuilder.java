package com.uber.rib.root.task_add_edit;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.uber.rib.core.InteractorBaseComponent;
import com.uber.rib.core.ViewBuilder;
import com.uber.rib.tutorial1.R;

import java.lang.annotation.Retention;

import javax.inject.Scope;
import javax.inject.Qualifier;

import dagger.Provides;
import dagger.Binds;
import dagger.BindsInstance;

import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Builder for the {@link TaskAddEditScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
public class TaskAddEditBuilder
    extends ViewBuilder<TaskAddEditView, TaskAddEditRouter, TaskAddEditBuilder.ParentComponent> {

  public TaskAddEditBuilder(ParentComponent dependency) {
    super(dependency);
  }

  /**
   * Builds a new {@link TaskAddEditRouter}.
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new {@link TaskAddEditRouter}.
   */
  public TaskAddEditRouter build(ViewGroup parentViewGroup) {
    TaskAddEditView view = createView(parentViewGroup);
    TaskAddEditInteractor interactor = new TaskAddEditInteractor();
    Component component = DaggerTaskAddEditBuilder_Component.builder()
        .parentComponent(getDependency())
        .view(view)
        .interactor(interactor)
        .build();
    return component.taskaddeditRouter();
  }

  @Override
  protected TaskAddEditView inflateView(LayoutInflater inflater, ViewGroup parentViewGroup) {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    TaskAddEditView taskAddEditView = (TaskAddEditView) inflater.inflate(R.layout.task_add_edit, parentViewGroup, false);

    return taskAddEditView;
  }

  public interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  public abstract static class Module {

    @TaskAddEditScope
    @Binds
    abstract TaskAddEditInteractor.TaskAddEditPresenter presenter(TaskAddEditView view);

    @TaskAddEditScope
    @Provides
    static TaskAddEditRouter router(
      Component component,
      TaskAddEditView view,
      TaskAddEditInteractor interactor) {
      return new TaskAddEditRouter(view, interactor, component);
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @TaskAddEditScope
  @dagger.Component(modules = Module.class,
       dependencies = ParentComponent.class)
  interface Component extends InteractorBaseComponent<TaskAddEditInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      Builder interactor(TaskAddEditInteractor interactor);
      @BindsInstance
      Builder view(TaskAddEditView view);
      Builder parentComponent(ParentComponent component);
      Component build();
    }
  }

  interface BuilderComponent  {
    TaskAddEditRouter taskaddeditRouter();
  }

  @Scope
  @Retention(CLASS)
  @interface TaskAddEditScope { }

  @Qualifier
  @Retention(CLASS)
  @interface TaskAddEditInternal { }
}
