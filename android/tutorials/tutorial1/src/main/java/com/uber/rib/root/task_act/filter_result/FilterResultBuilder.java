package com.uber.rib.root.task_act.filter_result;

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
 * Builder for the {@link FilterResultScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
public class FilterResultBuilder
    extends ViewBuilder<FilterResultView, FilterResultRouter, FilterResultBuilder.ParentComponent> {

  public FilterResultBuilder(ParentComponent dependency) {
    super(dependency);
  }

  /**
   * Builds a new {@link FilterResultRouter}.
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new {@link FilterResultRouter}.
   */
  public FilterResultRouter build(ViewGroup parentViewGroup) {
    FilterResultView view = createView(parentViewGroup);
    FilterResultInteractor interactor = new FilterResultInteractor();
    Component component = DaggerFilterResultBuilder_Component.builder()
        .parentComponent(getDependency())
        .view(view)
        .interactor(interactor)
        .build();
    return component.filterresultRouter();
  }

  @Override
  protected FilterResultView inflateView(LayoutInflater inflater, ViewGroup parentViewGroup) {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    FilterResultView filterResultView = (FilterResultView) inflater.inflate(R.layout.filter_result, parentViewGroup, false);
    return filterResultView;
  }

  public interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  public abstract static class Module {

    @FilterResultScope
    @Binds
    abstract FilterResultInteractor.FilterResultPresenter presenter(FilterResultView view);

    @FilterResultScope
    @Provides
    static FilterResultRouter router(
      Component component,
      FilterResultView view,
      FilterResultInteractor interactor) {
      return new FilterResultRouter(view, interactor, component);
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @FilterResultScope
  @dagger.Component(modules = Module.class,
       dependencies = ParentComponent.class)
  interface Component extends InteractorBaseComponent<FilterResultInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      Builder interactor(FilterResultInteractor interactor);
      @BindsInstance
      Builder view(FilterResultView view);
      Builder parentComponent(ParentComponent component);
      Component build();
    }
  }

  interface BuilderComponent  {
    FilterResultRouter filterresultRouter();
  }

  @Scope
  @Retention(CLASS)
  @interface FilterResultScope { }

  @Qualifier
  @Retention(CLASS)
  @interface FilterResultInternal { }
}
