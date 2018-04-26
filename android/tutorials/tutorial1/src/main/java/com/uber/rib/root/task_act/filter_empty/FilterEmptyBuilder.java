package com.uber.rib.root.task_act.filter_empty;

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
 * Builder for the {@link FilterEmptyScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
public class FilterEmptyBuilder
    extends ViewBuilder<FilterEmptyView, FilterEmptyRouter, FilterEmptyBuilder.ParentComponent> {

  public FilterEmptyBuilder(ParentComponent dependency) {
    super(dependency);
  }

  /**
   * Builds a new {@link FilterEmptyRouter}.
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new {@link FilterEmptyRouter}.
   */
  public FilterEmptyRouter build(ViewGroup parentViewGroup) {
    FilterEmptyView view = createView(parentViewGroup);
    FilterEmptyInteractor interactor = new FilterEmptyInteractor();
    Component component = DaggerFilterEmptyBuilder_Component.builder()
        .parentComponent(getDependency())
        .view(view)
        .interactor(interactor)
        .build();
    return component.filteremptyRouter();
  }

  @Override
  protected FilterEmptyView inflateView(LayoutInflater inflater, ViewGroup parentViewGroup) {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    FilterEmptyView filterEmptyView = (FilterEmptyView) inflater.inflate(R.layout.filter_empty, parentViewGroup, false);
    return filterEmptyView;
  }

  public interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  public abstract static class Module {

    @FilterEmptyScope
    @Binds
    abstract FilterEmptyInteractor.FilterEmptyPresenter presenter(FilterEmptyView view);

    @FilterEmptyScope
    @Provides
    static FilterEmptyRouter router(
      Component component,
      FilterEmptyView view,
      FilterEmptyInteractor interactor) {
      return new FilterEmptyRouter(view, interactor, component);
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @FilterEmptyScope
  @dagger.Component(modules = Module.class,
       dependencies = ParentComponent.class)
  interface Component extends InteractorBaseComponent<FilterEmptyInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      Builder interactor(FilterEmptyInteractor interactor);
      @BindsInstance
      Builder view(FilterEmptyView view);
      Builder parentComponent(ParentComponent component);
      Component build();
    }
  }

  interface BuilderComponent  {
    FilterEmptyRouter filteremptyRouter();
  }

  @Scope
  @Retention(CLASS)
  @interface FilterEmptyScope { }

  @Qualifier
  @Retention(CLASS)
  @interface FilterEmptyInternal { }
}
