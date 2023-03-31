package com.example.movielist.network;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.example.movielist.models.UserModel;
import com.example.movielist.services.SharedPreferencesOperations;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class FirebaseOperations {
    SharedPreferencesOperations sh;
    Activity a;
    View v;
    public FirebaseOperations(Activity a, View v){
        this.v = v;
        this.a = a;
        try {
            this.sh = new SharedPreferencesOperations(a);
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public final FirebaseFirestore db = FirebaseFirestore.getInstance();
    public void createUserWithEmailAndPassword(String email, String pass, String name, FirebaseCallback callback){
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(a, task -> {
            if (task.isSuccessful()){
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null){
                    System.out.println(user);
                    user.updateProfile(new UserProfileChangeRequest.Builder().setDisplayName(name).build());
                    addUserInDb(user.getEmail(), user.getDisplayName(), user.getUid());
                    Log.d("Register", "signInWithEmail:success");
                    sh.saveUserInfo(true, user.getUid());
                    callback.onSuccess();
                }
            } else {
                System.out.println(task.getException());
                Snackbar.make(v, String.valueOf(task.getException()), Snackbar.LENGTH_SHORT).show();
                callback.onError();
            }
        });
    }

    public void signInWithEmailAndPassword(String email, String pass, FirebaseCallback callback){
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null){
                    Log.d("Register", "signInWithEmail:success");
                    sh.saveUserInfo(true, user.getUid());
                    callback.onSuccess();
                }
            } else {
                Snackbar.make(v, task.getException().getMessage(), Snackbar.LENGTH_SHORT).show();
                callback.onError();
            }
        });
    }

    public void signOut(){
        mAuth.signOut();
    }

    public void addUserInDb(String email, String name, String uid){
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("email", email);
        user.put("uid", uid);

        db.collection("users").document(uid).set(user).addOnCompleteListener(a, task -> Log.d("DB", "User Added Successfully"));
    }
}
