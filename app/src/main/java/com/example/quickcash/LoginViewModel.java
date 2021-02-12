package com.example.quickcash;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.quickcash.View.LoginFragment;
import com.example.quickcash.View.LoginFragmentDirections;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginViewModel extends AndroidViewModel implements Observable {

    public NavController navController;

    @Bindable
    public String email = "", password= "";
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
        validLogin = email.equalsIgnoreCase("validEmail@dal.ca");
        if(validLogin){
            DBAuth = FirebaseAuth.getInstance();

            //TODO: Navigate to dashboard from here
            Toast.makeText(getApplication(), "Valid Login", Toast.LENGTH_LONG).show();
        }
        if(!validLogin){
            Toast.makeText(getApplication(), "Invalid Login", Toast.LENGTH_LONG).show();
        }
    }


    public void goToRegistration(){
        navController.navigate(R.id.registrationFragment);
    }


    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
}
