package com.example.movielist.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.movielist.fragments.CastFragment;
import com.example.movielist.fragments.RelatedFragment;
import com.example.movielist.fragments.ReviewFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new RelatedFragment();
        }
        if (position == 1){
            return new CastFragment();
        }
        if (position == 2){
            return new ReviewFragment();
        }
        return new RelatedFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
