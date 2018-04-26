package com.uber.rib.root.show_list;

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
 * Builder for the {@link ShowListScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
public class ShowListBuilder
    extends ViewBuilder<ShowListView, ShowListRouter, ShowListBuilder.ParentComponent> {

  public ShowListBuilder(ParentComponent dependency) {
    super(dependency);
  }

  /**
   * Builds a new {@link ShowListRouter}.
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new {@link ShowListRouter}.
   */
  public ShowListRouter build(ViewGroup parentViewGroup) {
    ShowListView view = createView(parentViewGroup);
    ShowListInteractor interactor = new ShowListInteractor();
    Component component = DaggerShowListBuilder_Component.builder()
        .parentComponent(getDependency())
        .view(view)
        .interactor(interactor)
        .build();
    return component.showlistRouter();
  }

  @Override
  protected ShowListView inflateView(LayoutInflater inflater, ViewGroup parentViewGroup) {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return (ShowListView) inflater.inflate(R.layout.show_list_rib, parentViewGroup, false);
  }

  public interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  public abstract static class Module {

    @ShowListScope
    @Binds
    abstract ShowListInteractor.ShowListPresenter presenter(ShowListView view);

    @ShowListScope
    @Provides
    static ShowListRouter router(
      Component component,
      ShowListView view,
      ShowListInteractor interactor) {
      return new ShowListRouter(view, interactor, component);
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @ShowListScope
  @dagger.Component(modules = Module.class,
       dependencies = ParentComponent.class)
  interface Component extends InteractorBaseComponent<ShowListInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      Builder interactor(ShowListInteractor interactor);
      @BindsInstance
      Builder view(ShowListView view);
      Builder parentComponent(ParentComponent component);
      Component build();
    }
  }

  interface BuilderComponent  {
    ShowListRouter showlistRouter();
  }

  @Scope
  @Retention(CLASS)
  @interface ShowListScope { }

  @Qualifier
  @Retention(CLASS)
  @interface ShowListInternal { }
}
