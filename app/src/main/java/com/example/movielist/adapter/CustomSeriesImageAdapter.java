package com.example.movielist.adapter;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielist.databinding.ActivityMainBinding;
import com.example.movielist.databinding.CustomMovieItemBinding;
import com.example.movielist.models.movies.Movie;
import com.example.movielist.models.series.Series;
import com.example.movielist.network.RetrofitClientInstance;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomSeriesImageAdapter extends RecyclerView.Adapter<CustomSeriesImageAdapter.CustomSeriesImageViewHolder> {

    Activity a;
    ActivityMainBinding activityMainBinding;
    public String IMAGE_BASE_URL = RetrofitClientInstance.IMAGE_BASE_URL;
    List<Series> Series;
    public CustomSeriesImageAdapter(List<Series> Series, Activity a){
        this.a = a;
        this.Series = Series;
        activityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(a));
    }


    @NonNull
    @Override
    public CustomSeriesImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomMovieItemBinding binding = CustomMovieItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CustomSeriesImageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomSeriesImageViewHolder holder, int position) {
        if (Series.get(holder.getAdapterPosition()).poster_path != null){
            Picasso.with(a).load(Uri.parse(IMAGE_BASE_URL + Series.get(holder.getAdapterPosition()).poster_path)).into(holder.posterImage);
        }
    }

    @Override
    public int getItemCount() {
        return Series.size();
    }

    static class CustomSeriesImageViewHolder extends RecyclerView.ViewHolder{

        AppCompatImageView posterImage;

        public CustomSeriesImageViewHolder(CustomMovieItemBinding binding){
            super(binding.getRoot());
            posterImage = binding.posterImage;
        }
    }
}
