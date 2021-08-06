package com.example.noteapp.onBoard;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import org.jetbrains.annotations.NotNull;

public class OnBoardAdapter extends FragmentStateAdapter {

    private int COUNT = 3;

    public OnBoardAdapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FirstFragment();

                break;
            case 1:
                fragment = new SecondFragment();
                break;
            case 2:
                fragment = new ThirdFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return COUNT;
    }
}
