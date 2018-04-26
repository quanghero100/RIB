package com.uber.rib.root.show_list;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.uber.rib.core.Initializer;
import com.uber.rib.tutorial1.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Top level view for {@link ShowListBuilder.ShowListScope}.
 */
class ShowListView extends LinearLayout implements ShowListInteractor.ShowListPresenter {

  RecyclerView recyclerView;
  NoteAdapter adapter;
  EditText etContent;
  Button btnAddNote;
  ArrayList<NoteData> listData = new ArrayList<NoteData>();
  public ShowListView(Context context) {
    this(context, null);
  }

  public ShowListView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ShowListView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Initializer
  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(mLayoutManager);
    adapter = new NoteAdapter(getContext(), listData);
    recyclerView.setAdapter(adapter);
    etContent = (EditText) findViewById(R.id.etContent);
    btnAddNote = (Button) findViewById(R.id.btnAddNote);
  }

  @Override
  public void loadDataShowList(ArrayList<NoteData> datas) {
    listData = datas;
    adapter.updateData(listData);
//    adapter.notifyDataSetChanged();
  }

  @Override
  public void addNote(String note) {
    listData.add(0, new NoteData(note));
    adapter.notifyDataSetChanged();
    etContent.setText("");
  }


    @Override
    public Observable<String> addNoteButtonClick() {
        return RxView.clicks(findViewById(R.id.btnAddNote))
            .map(
                new Function<Object, String>() {
                    @Override
                    public String apply(Object irrelevant) throws Exception {
                        return etContent.getText().toString();
                    }
                });
    }

  public static class NoteViewHolder extends RecyclerView.ViewHolder {

    TextView tvContent;
    public NoteViewHolder(View view) {
      super(view);
      tvContent = (TextView) view.findViewById(R.id.tvContent);
    }
  }


  public static class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {
    List<NoteData> datas;
    Context mContext;
    NoteAdapter(Context context, List<NoteData> listData) {
      mContext = context;
      datas = listData;
    }

    void updateData(List<NoteData> listData) {
      datas = listData;
      this.notifyDataSetChanged();
    }


    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
      return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
      NoteData note = datas.get(position);
      holder.tvContent.setText(note.getNoteContent());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

  }
}
