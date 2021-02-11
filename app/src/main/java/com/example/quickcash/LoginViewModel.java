package com.example.quickcash;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginViewModel extends AndroidViewModel implements Observable {

    @Bindable
    public String email = "viewModel email", password= "viewModel password";
    @Bindable
    public boolean validLogin;

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    //DB connections
    FirebaseDatabase DB;
    DatabaseReference userTypeRef;
    FirebaseAuth DBAuth;

    /* TODO: Donovan here's where you can put functionality for validation */

    public void validateLogin(){
        //Check firebase to see if this is a valid login
        validLogin = email.equalsIgnoreCase("RachaelMarie09@dal.ca");
        if(validLogin){
            Toast.makeText(getApplication(), "Valid Login" +validLogin, Toast.LENGTH_LONG).show();
            //ideally navigate to dashboard
        }
        if(!validLogin){
            Toast.makeText(getApplication(), "Invalid Login" + validLogin, Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
}
