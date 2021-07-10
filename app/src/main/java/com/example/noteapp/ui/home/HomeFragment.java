package com.example.noteapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentHomeBinding;
import com.example.noteapp.ui.note.NoteAdapter;
import com.example.noteapp.ui.note.NoteModel;

import org.jetbrains.annotations.NotNull;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private NoteAdapter adapter = new NoteAdapter();
    private FragmentHomeBinding binding;
    NoteModel model;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerViewHomeFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewHomeFragment.setAdapter(adapter);
        getFragmentData();

    }

    private void getFragmentData() {
        getParentFragmentManager().setFragmentResultListener("title", getViewLifecycleOwner(),(requestKey, result) -> {
             model = (NoteModel) result.getSerializable("model");
            adapter.addTask(model);
    });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}