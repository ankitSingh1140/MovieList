package com.example.movielist.services;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class AuthService {
    public boolean validationForEmail(TextInputEditText email){
        String pattern = "^(.+)@(\\S+)$";
        String emailText = String.valueOf(email.getText());
        if (emailText.length() == 0){
            email.setError("Please Enter Your Email");
            return false;
        } else if (Pattern.compile(pattern).matcher(emailText).matches()){
            return true;
        } else {
            email.setError("Please Enter a Valid Email");
            return false;
        }
    }

    public boolean validationForName(TextInputEditText name){
        String nameText = String.valueOf(name.getText());
        if (nameText.length() == 0){
            name.setError("Please Enter Your Name");
            return false;
        } else if (!nameText.contains(" ")){
            name.setError("Please Enter Your Full name");
            return false;
        }else return true;
    }

    public boolean validationForPass(TextInputEditText pass){
        String passText = String.valueOf(pass.getText());
        if (passText.length()==0){
            pass.setError("Please Enter Your Password");
            return false;
        } else if (passText.length() < 6){
            pass.setError("Minimum length of password should be 6");
            return false;
        } else return true;
    }

    public boolean validationForCPass(TextInputEditText pass, TextInputEditText cPass){
        String passText = String.valueOf(pass.getText());
        String cPassText = String.valueOf(cPass.getText());
        if (!cPassText.equals(passText)){
            cPass.setError("Password and Confirm Password must be same");
            return false;
        } else return true;
    }

    public  boolean validationForRegister(TextInputEditText email, TextInputEditText name, TextInputEditText pass, TextInputEditText cPass){
        return validationForEmail(email) && validationForName(name) && validationForPass(pass) && validationForCPass(pass,cPass);
    }

    public boolean validationForLogin(TextInputEditText email, TextInputEditText pass){
        return validationForEmail(email) && validationForPass(pass);
    }

}
