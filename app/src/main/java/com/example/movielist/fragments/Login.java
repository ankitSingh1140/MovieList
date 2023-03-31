package com.example.movielist.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.movielist.R;
import com.example.movielist.databinding.ActivityMainBinding;
import com.example.movielist.databinding.FragmentLoginBinding;
import com.example.movielist.network.FirebaseCallback;
import com.example.movielist.network.FirebaseOperations;
import com.example.movielist.services.AuthService;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;


public class Login extends Fragment {
    Activity a;
    FragmentLoginBinding binding;
    ActivityMainBinding activityMainBinding;
    AppCompatTextView registerButton;
    Button loginButton;
    TextInputEditText email;
    TextInputEditText pass;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    AuthService authService;
    FirebaseOperations firebaseOperations;


    public Login() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        a = getActivity();
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        registerButton = binding.buttonRegister;
        loginButton = binding.buttonLogin;
        email = binding.loginEmail;
        pass = binding.loginPassword;
        firebaseOperations = new FirebaseOperations(a, binding.getRoot());
        authService = new AuthService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = getParentFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction().setReorderingAllowed(true).addToBackStack(null);
        registerButton.setOnClickListener(v -> fragmentTransaction.replace(activityMainBinding.fragment1.getId(), Register.class, null).commit());
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                authService.validationForEmail(email);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                authService.validationForPass(pass);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        loginButton.setOnClickListener(v -> login());
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    public void login(){
        if (authService.validationForLogin(email, pass)){
            firebaseOperations.signInWithEmailAndPassword(String.valueOf(email.getText()), String.valueOf(pass.getText()), new FirebaseCallback() {
                @Override
                public void onSuccess() {
                    fragmentTransaction.replace(activityMainBinding.fragment1.getId(), BottomNavigation.class, null).commit();
                }

                @Override
                public void onError() {
                    Snackbar.make(binding.getRoot(), "Sign In failed", Snackbar.LENGTH_SHORT).show();
                }
            });
        }
    }
}