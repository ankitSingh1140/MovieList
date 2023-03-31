package com.example.movielist.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.movielist.R;
import com.example.movielist.adapter.CustomCategoriesAdapter;
import com.example.movielist.adapter.CustomMovieImageAdapter;
import com.example.movielist.adapter.CustomSeriesImageAdapter;
import com.example.movielist.databinding.FragmentHomeScreenBinding;
import com.example.movielist.models.categories.Category;
import com.example.movielist.models.movies.Movie;
import com.example.movielist.models.series.Series;
import com.example.movielist.network.ApiCallsMovie;
import com.example.movielist.network.ApiCallsSeries;

import java.util.List;

public class HomeScreen extends Fragment {
    static Activity a;
    static FragmentHomeScreenBinding binding;
    static RecyclerView recyclerView;
    ApiCallsMovie apiCallsMovie;
    ApiCallsSeries apiCallsSeries;



    public HomeScreen() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        a = getActivity();
        binding = FragmentHomeScreenBinding.inflate(getLayoutInflater());
        apiCallsMovie = new ApiCallsMovie(a, binding.getRoot());
        apiCallsSeries = new ApiCallsSeries(a, binding.getRoot());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding.movieItem.itemName.setText(R.string.movie);
        binding.seriesItem.itemName.setText(R.string.series);
        binding.movieItem.customItem.setBackgroundColor(Color.parseColor("#d7dcff"));
        setMovies();
        binding.movieItem.customItem.setOnClickListener( v -> {
            binding.movieItem.customItem.setBackgroundColor(Color.parseColor("#d7dcff"));
            binding.seriesItem.customItem.setBackgroundColor(Color.TRANSPARENT);
            setMovies();
        });
        binding.seriesItem.customItem.setOnClickListener(v -> {
            binding.seriesItem.customItem.setBackgroundColor(Color.parseColor("#d7dcff"));
            binding.movieItem.customItem.setBackgroundColor(Color.TRANSPARENT);
            setSeries();
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    public static void setCategory(List<Category> categories){
        recyclerView = binding.dashboardItems.recyclerViewCategories;
        CustomCategoriesAdapter adapter = new CustomCategoriesAdapter(categories, a);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(a,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public static void setUpcomingMovies(List<Movie> movies){
        recyclerView = binding.dashboardItems.recyclerViewLatest;
        CustomMovieImageAdapter adapter = new CustomMovieImageAdapter(movies, a);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(a,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public static void setPopularMovies(List<Movie> movies){
        recyclerView = binding.dashboardItems.recyclerViewPopular;
        CustomMovieImageAdapter adapter = new CustomMovieImageAdapter(movies, a);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(a,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public static void setNowPlayingMovies(List<Movie> movies){
        recyclerView = binding.dashboardItems.recyclerViewNowPlaying;
        CustomMovieImageAdapter adapter = new CustomMovieImageAdapter(movies, a);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(a,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public static void setTopRatedMovies(List<Movie> movies){
        recyclerView = binding.dashboardItems.recyclerViewTopRated;
        CustomMovieImageAdapter adapter = new CustomMovieImageAdapter(movies, a);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(a,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public static void setUpcomingSeries(List<Series> series){
        recyclerView = binding.dashboardItems.recyclerViewLatest;
        CustomSeriesImageAdapter adapter = new CustomSeriesImageAdapter(series, a);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(a,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public static void setPopularSeries(List<Series> series){
        recyclerView = binding.dashboardItems.recyclerViewPopular;
        CustomSeriesImageAdapter adapter = new CustomSeriesImageAdapter(series, a);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(a,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public static void setNowPlayingSeries(List<Series> series){
        recyclerView = binding.dashboardItems.recyclerViewNowPlaying;
        CustomSeriesImageAdapter adapter = new CustomSeriesImageAdapter(series, a);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(a,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public static void setTopRatedSeries(List<Series> series){
        recyclerView = binding.dashboardItems.recyclerViewTopRated;
        CustomSeriesImageAdapter adapter = new CustomSeriesImageAdapter(series, a);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(a,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    public void setMovies(){
        apiCallsMovie.getCategories();
        apiCallsMovie.getUpcoming();
        apiCallsMovie.getPopular();
        apiCallsMovie.getNowPlaying();
        apiCallsMovie.getTopRated();
    }

    public void setSeries(){
        apiCallsSeries.getCategories();
        apiCallsSeries.getUpcoming();
        apiCallsSeries.getPopular();
        apiCallsSeries.getNowPlaying();
        apiCallsSeries.getTopRated();
    }
}