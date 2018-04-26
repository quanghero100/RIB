package com.uber.rib.root.common.navigation_drawer;

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
 * Builder for the {@link NavigationDrawerScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
public class NavigationDrawerBuilder
    extends ViewBuilder<NavigationDrawerView, NavigationDrawerRouter, NavigationDrawerBuilder.ParentComponent> {

  public NavigationDrawerBuilder(ParentComponent dependency) {
    super(dependency);
  }

  /**
   * Builds a new {@link NavigationDrawerRouter}.
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new {@link NavigationDrawerRouter}.
   */
  public NavigationDrawerRouter build(ViewGroup parentViewGroup) {
    NavigationDrawerView view = createView(parentViewGroup);
    NavigationDrawerInteractor interactor = new NavigationDrawerInteractor();
    Component component = DaggerNavigationDrawerBuilder_Component.builder()
        .parentComponent(getDependency())
        .view(view)
        .interactor(interactor)
        .build();
    return component.navigationdrawerRouter();
  }

  @Override
  protected NavigationDrawerView inflateView(LayoutInflater inflater, ViewGroup parentViewGroup) {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    NavigationDrawerView drawerView = (NavigationDrawerView) inflater.inflate(R.layout.navigation_view, parentViewGroup, false);

    return drawerView;
  }

  public interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
    NavigationDrawerInteractor.Listener listener();
  }

  @dagger.Module
  public abstract static class Module {

    @NavigationDrawerScope
    @Binds
    abstract NavigationDrawerInteractor.NavigationDrawerPresenter presenter(NavigationDrawerView view);

    @NavigationDrawerScope
    @Provides
    static NavigationDrawerRouter router(
      Component component,
      NavigationDrawerView view,
      NavigationDrawerInteractor interactor) {
      return new NavigationDrawerRouter(view, interactor, component);
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @NavigationDrawerScope
  @dagger.Component(modules = Module.class,
       dependencies = ParentComponent.class)
  interface Component extends InteractorBaseComponent<NavigationDrawerInteractor>,
          BuilderComponent,
          NavigationDrawerBuilder.ParentComponent{

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      Builder interactor(NavigationDrawerInteractor interactor);
      @BindsInstance
      Builder view(NavigationDrawerView view);
      Builder parentComponent(ParentComponent component);
      Component build();
    }
  }

  interface BuilderComponent  {
    NavigationDrawerRouter navigationdrawerRouter();
  }

  @Scope
  @Retention(CLASS)
  @interface NavigationDrawerScope { }

  @Qualifier
  @Retention(CLASS)
  @interface NavigationDrawerInternal { }
}
