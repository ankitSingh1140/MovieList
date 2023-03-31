package com.example.movielist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.movielist.databinding.ActivityMainBinding;
import com.example.movielist.fragments.BottomNavigation;
import com.example.movielist.fragments.HomeScreen;
import com.example.movielist.fragments.Login;
import com.example.movielist.network.FirebaseOperations;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    FirebaseOperations firebaseOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        firebaseOperations = new FirebaseOperations(this, activityMainBinding.getRoot());
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().setReorderingAllowed(true);
        FirebaseUser currentUser = firebaseOperations.mAuth.getCurrentUser();
        if (currentUser != null){
            fragmentTransaction.add(activityMainBinding.fragment1.getId(), BottomNavigation.class, null).commit();
        } else {
            fragmentTransaction.add(activityMainBinding.fragment1.getId(), Login.class, null).commit();
        }
    }
}