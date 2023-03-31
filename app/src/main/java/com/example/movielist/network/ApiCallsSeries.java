package com.example.movielist.network;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.movielist.fragments.HomeScreen;
import com.example.movielist.models.categories.Category;
import com.example.movielist.models.categories.Result;
import com.example.movielist.models.series.Series;
import com.example.movielist.models.series.SeriesRoot;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCallsSeries {
    Activity a;
    View v;
    GetDataService service;
    public ApiCallsSeries(Activity a, View v){
        this.a = a;
        this.v = v;
        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
    }

    public void getCategories(){
        Call<Result> call = service.getSeriesCategories();
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
        Call<SeriesRoot> call = service.getSeriesUpcoming();
        call.enqueue(new Callback<SeriesRoot>() {
            @Override
            public void onResponse(@NonNull Call<SeriesRoot> call, @NonNull Response<SeriesRoot> response) {
                if (response.body() != null){
                    List<Series> series = new ArrayList<>(response.body().results);
                    HomeScreen.setUpcomingSeries(series);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SeriesRoot> call, @NonNull Throwable t) {

            }
        });
    }
    public void getPopular(){
        Call<SeriesRoot> call = service.getSeriesPopular();
        call.enqueue(new Callback<SeriesRoot>() {
            @Override
            public void onResponse(@NonNull Call<SeriesRoot> call, @NonNull Response<SeriesRoot> response) {
                if (response.body() != null){
                    List<Series> series = new ArrayList<>(response.body().results);
                    HomeScreen.setPopularSeries(series);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SeriesRoot> call, @NonNull Throwable t) {

            }
        });
    }
    public void getNowPlaying(){
        Call<SeriesRoot> call = service.getSeriesNowPlaying();
        call.enqueue(new Callback<SeriesRoot>() {
            @Override
            public void onResponse(@NonNull Call<SeriesRoot> call, @NonNull Response<SeriesRoot> response) {
                if (response.body() != null){
                    List<Series> series = new ArrayList<>(response.body().results);
                    HomeScreen.setNowPlayingSeries(series);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SeriesRoot> call, @NonNull Throwable t) {

            }
        });
    }
    public void getTopRated(){
        Call<SeriesRoot> call = service.getSeriesTopRated();
        call.enqueue(new Callback<SeriesRoot>() {
            @Override
            public void onResponse(@NonNull Call<SeriesRoot> call, @NonNull Response<SeriesRoot> response) {
                if (response.body() != null){
                    List<Series> series = new ArrayList<>(response.body().results);
                    HomeScreen.setTopRatedSeries(series);
                }
            }

            @Override
            public void onFailure(@NonNull Call<SeriesRoot> call, @NonNull Throwable t) {

            }
        });
    }

}
