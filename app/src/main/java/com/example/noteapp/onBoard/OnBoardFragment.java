package com.example.noteapp.onBoard;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentOnBoardBinding;
import com.example.noteapp.preference.PreferenceHelper;

public class OnBoardFragment extends Fragment {
    FragmentOnBoardBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnBoardBinding.inflate(getLayoutInflater(), container, false);
        init_viewpager_adapter();
        transitionFromOnBoarding();
        closeOnBoarding();
        return binding.getRoot();
    }

    private void closeOnBoarding() {
        binding.txtSkip.setOnClickListener(v -> {
            PreferenceHelper sPrerf = new PreferenceHelper();
            sPrerf.init(requireContext());
            sPrerf.onSaveOnBoard();
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigateUp();
        });

        binding.txtSkip1.setOnClickListener(v -> {
            PreferenceHelper sPrerf = new PreferenceHelper();
            sPrerf.init(requireContext());
            sPrerf.onSaveOnBoard();
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
            navController.navigateUp();
        });
    }

    private void transitionFromOnBoarding() {
        binding.viewPagerOnBoard.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        binding.txtSkip1.setVisibility(View.GONE);
                        binding.txtSkip.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        binding.txtSkip.setVisibility(View.VISIBLE);
                        binding.txtSkip1.setVisibility(View.GONE);
                        break;
                    case 2:
                        binding.txtSkip1.setVisibility(View.VISIBLE);
                        binding.txtSkip.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }


    private void init_viewpager_adapter() {
        if (binding.viewPagerOnBoard != null) {
            OnBoardAdapter onBoardAdapter = new OnBoardAdapter(getActivity());
            binding.viewPagerOnBoard.setAdapter(onBoardAdapter);
            binding.springDotsIndicator.setViewPager2(binding.viewPagerOnBoard);

        }
    }
}