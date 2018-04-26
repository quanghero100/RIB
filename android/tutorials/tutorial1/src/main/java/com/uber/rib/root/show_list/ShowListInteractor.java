package com.uber.rib.root.show_list;

import android.support.annotation.Nullable;

import com.uber.rib.core.Bundle;
import com.uber.rib.core.Interactor;
import com.uber.rib.core.RibInteractor;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Coordinates Business Logic for {@link ShowListBuilder.ShowListScope}.
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
public class ShowListInteractor
    extends Interactor<ShowListInteractor.ShowListPresenter, ShowListRouter> {

  @Inject ShowListPresenter presenter;

  @Override
  protected void didBecomeActive(@Nullable Bundle savedInstanceState) {
    super.didBecomeActive(savedInstanceState);

    // TODO: Add attachment logic here (RxSubscriptions, etc.).
    presenter.loadDataShowList(loadData());
    presenter.addNoteButtonClick()
            .subscribe(new Consumer<String>() {
              @Override
              public void accept(String s) throws Exception {
                presenter.addNote(s);
              }
            });


  }



  @Override
  protected void willResignActive() {
    super.willResignActive();

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }


  ArrayList<NoteData> loadData() {
    NoteData[] elements = {
            new NoteData("Lam viec 1"),
            new NoteData("Lam viec 1"),
            new NoteData("Lam viec 1"),
            new NoteData("Lam viec 1"),
            new NoteData("Lam viec 1"),
            new NoteData("Lam viec 1"),
            new NoteData("Lam viec 1"),
            new NoteData("Lam viec 1"),


    };

    ArrayList<NoteData> datas = new ArrayList<NoteData>(Arrays.asList(elements));

    return datas;
  }


  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface ShowListPresenter {
    void loadDataShowList(ArrayList<NoteData> datas);
    void addNote(String note);
    Observable<String> addNoteButtonClick();

  }
}
