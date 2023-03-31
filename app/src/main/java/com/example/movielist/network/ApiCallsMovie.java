package com.example.movielist.network;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.movielist.fragments.HomeScreen;
import com.example.movielist.models.categories.Category;
import com.example.movielist.models.categories.Result;
import com.example.movielist.models.movies.Movie;
import com.example.movielist.models.movies.MoviesRoot;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCallsMovie {
    Activity a;
    View v;
    GetDataService service;
    public ApiCallsMovie(Activity a, View v){
        this.a = a;
        this.v = v;
        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
    }

    public void getCategories(){
        Call<Result> call = service.getMoviesCategories();
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(@NonNull Call<Result> call, @NonNull Response<Result> response) {
                if (response.body() != null){
                    List<Category> categories = new ArrayList<>(response.body().genres);
                    HomeScreen.setCategory(categories);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result> call, @NonNull Throwable t) {

            }
        });
    }

    public void getUpcoming(){
        Call<MoviesRoot> call = service.getUpcomingMovies();
        call.enqueue(new Callback<MoviesRoot>() {
            @Override
            public void onResponse(@NonNull Call<MoviesRoot> call, @NonNull Response<MoviesRoot> response) {
                if (response.body() != null){
                    List<Movie> movies = new ArrayList<>(response.body().results);
                    HomeScreen.setUpcomingMovies(movies);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesRoot> call, @NonNull Throwable t) {

            }
        });
    }
    public void getPopular(){
        Call<MoviesRoot> call = service.getPopularMovies();
        call.enqueue(new Callback<MoviesRoot>() {
            @Override
            public void onResponse(@NonNull Call<MoviesRoot> call, @NonNull Response<MoviesRoot> response) {
                if (response.body() != null){
                    List<Movie> movies = new ArrayList<>(response.body().results);
                    HomeScreen.setPopularMovies(movies);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesRoot> call, @NonNull Throwable t) {

            }
        });
    }
    public void getNowPlaying(){
        Call<MoviesRoot> call = service.getNowPlayingMovies();
        call.enqueue(new Callback<MoviesRoot>() {
            @Override
            public void onResponse(@NonNull Call<MoviesRoot> call, @NonNull Response<MoviesRoot> response) {
                if (response.body() != null){
                    List<Movie> movies = new ArrayList<>(response.body().results);
                    HomeScreen.setNowPlayingMovies(movies);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesRoot> call, @NonNull Throwable t) {

            }
        });
    }
    public void getTopRated(){
        Call<MoviesRoot> call = service.getTopRatedMovies();
        call.enqueue(new Callback<MoviesRoot>() {
            @Override
            public void onResponse(@NonNull Call<MoviesRoot> call, @NonNull Response<MoviesRoot> response) {
                if (response.body() != null){
                    List<Movie> movies = new ArrayList<>(response.body().results);
                    HomeScreen.setTopRatedMovies(movies);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MoviesRoot> call, @NonNull Throwable t) {

            }
        });
    }
}
