package com.example.movielist.adapter;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielist.databinding.CustomMovieItemBinding;
import com.example.movielist.databinding.FragmentBottomNavigationBinding;
import com.example.movielist.fragments.MovieDetailsFragment;
import com.example.movielist.models.movies.Movie;
import com.example.movielist.network.RetrofitClientInstance;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomMovieImageAdapter extends RecyclerView.Adapter<CustomMovieImageAdapter.CustomMovieImageViewHolder> {

    Activity a;
    FragmentBottomNavigationBinding fragmentBottomNavigationBinding;
    public String IMAGE_BASE_URL = RetrofitClientInstance.IMAGE_BASE_URL;
    List<Movie> movies;
    public CustomMovieImageAdapter(List<Movie> movies, Activity a){
        this.a = a;
        this.movies = movies;
        fragmentBottomNavigationBinding = FragmentBottomNavigationBinding.inflate(LayoutInflater.from(a));

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
            holder.posterImage.setOnClickListener( v -> {
                MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment(movies.get(holder.getAdapterPosition()).id);
                FragmentActivity fragmentActivity = (FragmentActivity) a;
                FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(fragmentBottomNavigationBinding.fragment2.getId(), movieDetailsFragment).addToBackStack("home").setReorderingAllowed(true).commit();
            });
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
