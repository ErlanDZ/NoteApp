package com.example.noteapp.onBoard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.example.noteapp.R;
import com.example.noteapp.databinding.FragmentOnBoardBinding;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import org.jetbrains.annotations.NotNull;

public class OnBoardFragment extends Fragment {
    FragmentOnBoardBinding binding;
    DotsIndicator dotsIndicator;
    ViewPager2 viewPager2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnBoardBinding.inflate(getLayoutInflater(),container,false);
        init_viewpager_adapter();
        return binding.getRoot();
    }




    private  void init_viewpager_adapter(){
        if (binding.viewPagerOnBoard != null){
            OnBoardAdapter onBoardAdapter = new OnBoardAdapter(getActivity());
            binding.viewPagerOnBoard.setAdapter(onBoardAdapter);
            binding.springDotsIndicator.setViewPager2(binding.viewPagerOnBoard);

        }
    }
}