package com.example.movielist.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
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
import com.example.movielist.databinding.FragmentRegisterBinding;
import com.example.movielist.network.FirebaseCallback;
import com.example.movielist.network.FirebaseOperations;
import com.example.movielist.services.AuthService;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;


public class Register extends Fragment {
    FragmentRegisterBinding binding;
    ActivityMainBinding activityMainBinding;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    AppCompatTextView loginButton;
    TextInputEditText email;
    TextInputEditText name;
    TextInputEditText pass;
    TextInputEditText cPass;
    Button registerButton;
    FirebaseOperations firebaseOperations;
    Activity a;
    AuthService authService;



    public Register() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        a = getActivity();
        binding = FragmentRegisterBinding.inflate(getLayoutInflater());
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        loginButton = binding.buttonLogin;
        registerButton = binding.buttonRegister;
        email = binding.registerEmail;
        name = binding.registerName;
        pass  = binding.registerPassword;
        cPass = binding.registerConfirmPassword;
        firebaseOperations = new FirebaseOperations(a, binding.getRoot());
        authService = new AuthService();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentManager = getParentFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction().setReorderingAllowed(true).addToBackStack(null);
        loginButton.setOnClickListener(v -> fragmentTransaction.replace(activityMainBinding.fragment1.getId(), Login.class, null).commit());
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
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                authService.validationForName(name);
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
        cPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                authService.validationForCPass(pass,cPass);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        registerButton.setOnClickListener(v -> register());
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    public void register(){
        if (authService.validationForRegister(email, name, pass, cPass)){
            firebaseOperations.createUserWithEmailAndPassword(String.valueOf(email.getText()), String.valueOf(pass.getText()), String.valueOf(name.getText()), new FirebaseCallback() {
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