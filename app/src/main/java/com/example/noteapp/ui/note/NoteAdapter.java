package com.example.noteapp.ui.note;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.noteapp.R;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    List<NoteModel> list = new ArrayList<>();


    @NonNull
    @NotNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    public void updateList(List<NoteModel> list1) {
        list = list1;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NoteAdapter.ViewHolder holder, int position) {
        holder.titleitem.setText(list.get(position).getTitle());
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
        TextView titleitem;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            titleitem = itemView.findViewById(R.id.txt_item);
        }
    }
}
