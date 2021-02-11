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
    public String email = "";
    @Bindable
    public String password= "";
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    //DB connections
    FirebaseDatabase DB;
    DatabaseReference userTypeRef;
    FirebaseAuth DBAuth;

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    /**
     * When the user clicks the login button
     */
    public void loginClicked(){

        if(validateLogin()){
       String message = "Welcome " + email;
        Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show(); //show welcome Message
            }

        else {
            String errorMessage = "Username or password is incorrect";
            Toast.makeText(getApplication(), errorMessage, Toast.LENGTH_LONG).show(); //show error Message
            }
        }


    /* TODO: Donovan here's where you can put functionality for validation */

    public boolean validateLogin(){
        //Check firebase to see if this is a valid login
        return true;
    }
}
