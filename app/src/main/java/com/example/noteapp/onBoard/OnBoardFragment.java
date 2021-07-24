package com.example.noteapp.onBoard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteapp.databinding.FragmentOnBoardBinding;

public class OnBoardFragment extends Fragment {
    FragmentOnBoardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnBoardBinding.inflate(getLayoutInflater(), container, false);
        init_viewpager_adapter();
        return binding.getRoot();
    }


    private void init_viewpager_adapter() {
        if (binding.viewPagerOnBoard != null) {
            OnBoardAdapter onBoardAdapter = new OnBoardAdapter(getActivity());
            binding.viewPagerOnBoard.setAdapter(onBoardAdapter);
            binding.springDotsIndicator.setViewPager2(binding.viewPagerOnBoard);

        }
    }
}