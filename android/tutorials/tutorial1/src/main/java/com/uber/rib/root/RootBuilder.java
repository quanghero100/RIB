/*
 * Copyright (C) 2017. Uber Technologies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uber.rib.root;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.uber.rib.RootActivity;
import com.uber.rib.core.InteractorBaseComponent;
import com.uber.rib.core.ViewBuilder;
import com.uber.rib.root.show_list.ShowListBuilder;
import com.uber.rib.root.task_act.TaskActBuilder;
import com.uber.rib.root.task_act.TaskActInteractor;
import com.uber.rib.root.task_add.TaskAddBuilder;
import com.uber.rib.root.task_add.TaskAddInteractor;
import com.uber.rib.root.task_detail.TaskDetailBuilder;
import com.uber.rib.tutorial1.R;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Provides;

import static java.lang.annotation.RetentionPolicy.CLASS;

/** Builder for the {@link RootScope}. */
public class RootBuilder extends ViewBuilder<RootView, RootRouter, RootBuilder.ParentComponent> {

//  public RibActivity getActivity() {
//    return mActivity;
//  }

  public RootActivity mActivity;

  public RootBuilder(ParentComponent dependency, RootActivity activity) {
    super(dependency);
    mActivity = activity;

  }

  /**
   * Builds a new {@link RootRouter}.
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new {@link RootRouter}.
   */
  public RootRouter build(ViewGroup parentViewGroup) {
    RootView view = createView(parentViewGroup);
    RootInteractor interactor = new RootInteractor();
    Component component = DaggerRootBuilder_Component.builder()
            .parentComponent(getDependency())
            .view(view)
            .interactor(interactor)
            .build();
    return component.rootRouter();
  }

  @Override
  protected RootView inflateView(LayoutInflater inflater, ViewGroup parentViewGroup) {
    RootView rootView = (RootView) inflater.inflate(R.layout.root_rib, parentViewGroup, false);
    rootView.setActivity(mActivity);
    return rootView;
  }

  public interface ParentComponent {
    // Define dependencies required from your parent interactor here.
    RootInteractor.RootListener listener();
  }

  @dagger.Module
  public abstract static class Module {

    @RootScope
    @Binds
    abstract RootInteractor.RootPresenter presenter(RootView view);

    @RootScope
    @Provides
    static RootRouter router(Component component,
                             RootView view,
                             RootInteractor interactor
                             ) {
      return new RootRouter(view,
              interactor,
              component,
              new TaskActBuilder(component, view.getActivity()),
              new TaskAddBuilder(component),
              new TaskDetailBuilder(component)
      );

    }

    @RootScope
    @Provides
    static TaskActInteractor.Listener taskActListener(RootInteractor interactor) {
      return interactor. new TaskActListener();
    }

    @RootScope
    @Provides
    static TaskAddInteractor.Listener taskAddListener(RootInteractor interactor) {
      return interactor. new TaskAddListener();
    }

  }

  @RootScope
  @dagger.Component(
    modules = Module.class,
    dependencies = ParentComponent.class
  )
  interface Component extends InteractorBaseComponent<RootInteractor>,
          BuilderComponent,
          TaskActBuilder.ParentComponent,
          ShowListBuilder.ParentComponent,
          TaskAddBuilder.ParentComponent,
          TaskDetailBuilder.ParentComponent

  {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      Builder interactor(RootInteractor interactor);

      @BindsInstance
      Builder view(RootView view);

      Builder parentComponent(ParentComponent component);

      Component build();
    }
  }



  interface BuilderComponent {
    RootRouter rootRouter();
  }

  @Scope
  @Retention(CLASS)
  @interface RootScope {}
}
