package com.example.movielist.models;

import java.util.HashMap;
import java.util.Map;

public class UserModel {
    public String name;
    public String uid;
    public String email;
    public UserModel(String name, String email, String uid){
        this.name = name;
        this.email = email;
        this.uid = uid;
    }
}
