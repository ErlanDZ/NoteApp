package com.example.noteapp.ui.note;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.onClick.OnClick;
import com.example.noteapp.R;
import com.example.noteapp.ui.home.HomeFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    List<NoteModel> list = new ArrayList<>();
    OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public NoteAdapter(boolean isChange, HomeFragment homeFragment) {


    }

    @NonNull
    @NotNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view;
        if (!HomeFragment.isChange) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_second, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);

        }

        return new ViewHolder(view);
    }


    public void updateList(List<NoteModel> list1) {
        list = list1;
        notifyDataSetChanged();
    }

    public void SetList(List<NoteModel> noteModel) {
        list = noteModel;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NoteAdapter.ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    public void addTask(NoteModel noteModel) {
        list.add(noteModel);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleitem, date;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            titleitem = itemView.findViewById(R.id.txt_item);
            date = itemView.findViewById(R.id.txt_data_item);
        }

        public void onBind(NoteModel noteModel) {
            titleitem.setText(noteModel.getTitle());
            date.setText(noteModel.getDate());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClick.click(noteModel, getAdapterPosition());
                }
            });
        }

    }

}
