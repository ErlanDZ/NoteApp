package com.example.noteapp.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentHomeBinding;
import com.example.noteapp.ui.note.NoteAdapter;
import com.example.noteapp.ui.note.NoteModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<NoteModel> models = new ArrayList<>();
    private NoteAdapter adapter = new NoteAdapter();
    HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    NoteModel model;
    public static boolean isChange = true;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    public void filter(String text) {
        ArrayList<NoteModel> filterAdList = new ArrayList<>();
        for (NoteModel noteModel : models) {
            if (noteModel.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filterAdList.add(noteModel);
            }
        }
        adapter.updateList(filterAdList);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecycler();
        setupSearch();
        getFragmentData();
    }

    private void setupSearch() {
        binding.editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void setupRecycler() {
        binding.recyclerViewHomeFragment.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            if (isChange) {
                item.setIcon(R.drawable.ic_baseline_list_24);
                binding.recyclerViewHomeFragment.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                isChange = false;
            } else {
                item.setIcon(R.drawable.dashboard);
                binding.recyclerViewHomeFragment.setLayoutManager(new LinearLayoutManager(getContext()));
                isChange = true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void getFragmentData() {
        getParentFragmentManager().setFragmentResultListener("title", getViewLifecycleOwner(), (requestKey, result) -> {
            model = (NoteModel) result.getSerializable("model");
            models.add(model);
            adapter.addTask(model);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}