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
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomMovieImageAdapter extends RecyclerView.Adapter<CustomMovieImageAdapter.CustomMovieImageViewHolder> {

    Activity a;
    ActivityMainBinding activityMainBinding;
    private static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original";
    List<Movie> movies;
    public CustomMovieImageAdapter(List<Movie> movies, Activity a){
        this.a = a;
        this.movies = movies;
        activityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(a));
    }


    @NonNull
    @Override
    public CustomMovieImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomMovieItemBinding binding = CustomMovieItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CustomMovieImageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomMovieImageViewHolder holder, int position) {
        if (movies.get(holder.getAdapterPosition()).poster_path != null){
            Picasso.with(a).load(Uri.parse(IMAGE_BASE_URL + movies.get(holder.getAdapterPosition()).poster_path)).into(holder.posterImage);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class CustomMovieImageViewHolder extends RecyclerView.ViewHolder{

        AppCompatImageView posterImage;

        public CustomMovieImageViewHolder(CustomMovieItemBinding binding){
            super(binding.getRoot());
            posterImage = binding.posterImage;
        }
    }
}
