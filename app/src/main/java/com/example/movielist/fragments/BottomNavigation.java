package com.example.movielist.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.movielist.R;
import com.example.movielist.databinding.FragmentBottomNavigationBinding;
import com.google.android.material.navigation.NavigationBarView;

public class BottomNavigation extends Fragment {
    FragmentBottomNavigationBinding binding;


    public BottomNavigation() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentBottomNavigationBinding.inflate(getLayoutInflater());
        setFragment(HomeScreen.class);
        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        setFragment(HomeScreen.class);
                        return true;
                    case R.id.fav:
                        return true;
                    case R.id.search:
                        return true;
                    case R.id.profile:
                        return true;

                }
                return false;
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    public void setFragment(@NonNull Class<? extends androidx.fragment.app.Fragment> fragmentClass){
        getParentFragmentManager().beginTransaction().replace(binding.fragment2.getId(), fragmentClass, null).commit();
    }
}