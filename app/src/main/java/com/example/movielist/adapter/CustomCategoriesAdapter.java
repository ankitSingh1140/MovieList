package com.example.movielist.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielist.databinding.ActivityMainBinding;
import com.example.movielist.databinding.CustomCategoryItemBinding;
import com.example.movielist.models.categories.Category;

import java.util.List;

public class CustomCategoriesAdapter extends RecyclerView.Adapter<CustomCategoriesAdapter.CustomCategoriesViewHolder>{
    Activity a;
    ActivityMainBinding activityMainBinding;
    List<Category> categories;

    public CustomCategoriesAdapter(List<Category> categories, Activity a){
        this.a = a;
        this.categories = categories;
        this.activityMainBinding = ActivityMainBinding.inflate(LayoutInflater.from(a));
    }
    @NonNull
    @Override
    public CustomCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomCategoryItemBinding binding = CustomCategoryItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CustomCategoriesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomCategoriesViewHolder holder, int position) {
        holder.categoryName.setText(categories.get(position).name);

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class CustomCategoriesViewHolder extends RecyclerView.ViewHolder{
        AppCompatTextView categoryName;

        public CustomCategoriesViewHolder(CustomCategoryItemBinding binding) {
            super(binding.getRoot());
            categoryName = binding.categoryName;
        }
    }
}
