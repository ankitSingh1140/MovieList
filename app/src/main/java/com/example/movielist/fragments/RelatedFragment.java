package com.example.movielist.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movielist.R;
import com.example.movielist.adapter.CustomMovieImageAdapter;
import com.example.movielist.databinding.FragmentHomeScreenBinding;
import com.example.movielist.databinding.FragmentRelatedBinding;
import com.example.movielist.models.movies.Movie;
import com.example.movielist.network.ApiCallsMovie;

import java.util.List;


public class RelatedFragment extends Fragment {
    static Activity a;
    static FragmentRelatedBinding binding;
    static RecyclerView recyclerView;
    ApiCallsMovie apiCallsMovie;

    public RelatedFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        a = getActivity();
        binding = FragmentRelatedBinding.inflate(getLayoutInflater());
        apiCallsMovie = new ApiCallsMovie(a, binding.getRoot());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        apiCallsMovie.getRecommended(MovieDetailsFragment.id);
        apiCallsMovie.getSimilar(MovieDetailsFragment.id);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    public static void setRecommendedMovies(List<Movie> movies){
        recyclerView = binding.recyclerViewRecommended;
        CustomMovieImageAdapter adapter = new CustomMovieImageAdapter(movies, a);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(a,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public static void setSimilarMovies(List<Movie> movies){
        recyclerView = binding.recyclerViewSimilar;
        CustomMovieImageAdapter adapter = new CustomMovieImageAdapter(movies, a);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(a,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}