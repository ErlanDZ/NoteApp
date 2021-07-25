package com.example.noteapp.ui.note;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentNoteBinding;
import com.example.noteapp.room.App;

public class NoteFragment extends Fragment {

    NoteModel noteModel;
    private FragmentNoteBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentNoteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        setClick(navController);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setClick(NavController navController) {
        binding.txtReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = binding.etNote.getText().toString();
                noteModel = new NoteModel(title);
                Bundle bundle = new Bundle();
                bundle.putSerializable("model", noteModel);
                getParentFragmentManager().setFragmentResult("title", bundle);
                App.getInstance().dao().insertTask(new NoteModel(binding.etNote.getText().toString()));
                navController.navigateUp();
            }
        });
        binding.etNote.setText("");
    }
}