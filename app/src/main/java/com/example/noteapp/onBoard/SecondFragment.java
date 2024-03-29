package com.example.noteapp.onBoard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentSecondBinding;
import com.example.noteapp.preference.PreferenceHelper;


public class SecondFragment extends Fragment {
    FragmentSecondBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(getLayoutInflater(), container, false);
//        skip_viewpager_second();
        return binding.getRoot();
    }

//    private void skip_viewpager_second() {
//        binding.txtSkip2.setOnClickListener(v -> {
//            PreferenceHelper sPrerf = new PreferenceHelper();
//            sPrerf.init(requireContext());
//            sPrerf.onSaveOnBoard();
//            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
//            navController.navigateUp();
//        });
//    }
}