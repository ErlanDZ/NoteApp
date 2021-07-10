package com.example.noteapp.ui.note;

import android.content.Context;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.databinding.ItemTaskBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    LayoutInflater layoutInflater;
    ItemTaskBinding binding;
    List<NoteModel> list = new ArrayList<>();



    @NonNull
    @NotNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
         binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NoteAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }
    public void addTask(NoteModel noteModel){
        list.add(noteModel);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

        }

        public void bind(NoteModel noteModel) {
            binding.txtItem.setText(noteModel.getTitle());
        }
    }
}
