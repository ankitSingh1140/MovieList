package com.example.movielist.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movielist.R;
import com.example.movielist.adapter.ViewPagerAdapter;
import com.example.movielist.databinding.FragmentMovieDetailsBinding;
import com.example.movielist.models.movies.MovieDetails;
import com.example.movielist.network.ApiCallsMovie;
import com.example.movielist.network.RetrofitClientInstance;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MovieDetailsFragment extends Fragment {

    static FragmentMovieDetailsBinding binding;
    static int id;
    ApiCallsMovie apiCallsMovie;
    ViewPagerAdapter adapter;
    List<String> tabs = new ArrayList<>(Arrays.asList("Related", "Cast", "Reviews"));
//    ViewPagerAdapter viewPagerAdapter;
    static Activity a;
    public static MovieDetails movieDetails;
    public static String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";



    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    public MovieDetailsFragment(int id){
        this.id = id;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        a = getActivity();
        binding = FragmentMovieDetailsBinding.inflate(getLayoutInflater());
        apiCallsMovie = new ApiCallsMovie(a, binding.getRoot());
        apiCallsMovie.getMovieDetails(id);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding.backButton.setOnClickListener(v -> getParentFragmentManager().popBackStackImmediate());
        adapter = new ViewPagerAdapter(getParentFragmentManager(), getLifecycle());
        binding.viewPager.setAdapter(adapter);
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> tab.setText(tabs.get(position))).attach();
        // Inflate the layout for this fragment
        return binding.getRoot();
    }


    public static void setMovieDetails(){
        if (movieDetails != null){
            if (movieDetails.backdrop_path != null){
                Picasso.with(a).load(Uri.parse(IMAGE_BASE_URL + movieDetails.backdrop_path)).into(binding.ivPosterImage);
            }
            binding.tvMovieName.setText(movieDetails.original_title);
            binding.tvTagLine.setText(movieDetails.tagline);
            binding.tvRatings.setText(String.valueOf(movieDetails.vote_average));
            binding.tvOverview.setText(movieDetails.overview);
            binding.tvReleaseDate.setText(movieDetails.release_date);
            binding.buttonFav.setOnClickListener(v -> {
                if (binding.buttonFav.isActivated()){
                    binding.buttonFav.setActivated(false);
                    setFavourite();
                } else {
                    binding.buttonFav.setActivated(true);
                    setFavourite();
                }
            });
        }
    }
    public static void setFavourite(){
        if (binding.buttonFav.isActivated()){
            DrawableCompat.setTint(binding.buttonFav.getDrawable(), Color.RED);
        } else {
            DrawableCompat.setTint(binding.buttonFav.getDrawable(), Color.BLACK);
        }

    }

}