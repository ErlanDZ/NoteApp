package com.example.noteapp.onBoard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentThirdBinding;
import com.example.noteapp.preference.PreferenceHelper;

public class ThirdFragment extends Fragment {
    FragmentThirdBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentThirdBinding.inflate(getLayoutInflater(), container, false);
        skip_viewpager();
        return binding.getRoot();
    }

    private void skip_viewpager() {
        binding.txtSkip1.setOnClickListener(v -> {
            PreferenceHelper sPrerf = new PreferenceHelper();
            sPrerf.init(requireContext());
            sPrerf.onSaveOnBoard();
            NavController navController = Navigation.findNavController(requireActivity(), R.id.view_pager_on_board);
            navController.navigate(R.id.action_onBoardFragment_to_nav_home);
        });
    }
}