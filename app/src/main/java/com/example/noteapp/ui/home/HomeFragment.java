package com.example.noteapp.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentHomeBinding;
import com.example.noteapp.room.App;
import com.example.noteapp.ui.note.NoteAdapter;
import com.example.noteapp.ui.note.NoteModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private List<NoteModel> models = new ArrayList<>();
    private NoteAdapter adapter;
    HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
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
        setUpdate();
        swipedDelete();
    }




    private void swipedDelete() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
                alertDialog.setTitle("ВНИМАНИЕ");
                alertDialog.setMessage("ТОЧНО УДАЛИТЬ????");
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        App.getInstance().dao().update(models.get(viewHolder.getAdapterPosition()));
                        alertDialog.dismiss();
                    }
                });

                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        App.getInstance().dao().delete(models.get(viewHolder.getAdapterPosition()));
                        Toast.makeText(getActivity(), "УДАЛЕНО", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.show();
            }
        }).attachToRecyclerView(binding.recyclerViewHomeFragment);
    }

    private void setUpdate() {
        adapter.setOnClick((noteModel, position) -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("mod", noteModel);
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.action_nav_home_to_noteFragment, bundle);
        });
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
        if (!isChange){
            binding.recyclerViewHomeFragment.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        }else {
            binding.recyclerViewHomeFragment.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        binding.recyclerViewHomeFragment.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        adapter = new NoteAdapter(isChange, HomeFragment.this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            if (isChange) {
                item.setIcon(R.drawable.ic_baseline_list_24);
                binding.recyclerViewHomeFragment
                        .setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
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
        App.getInstance().dao().getAll().observe(requireActivity(),noteModels -> {
            adapter.SetList(noteModels);
            models = noteModels;
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}