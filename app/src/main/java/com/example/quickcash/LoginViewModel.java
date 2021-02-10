package com.example.quickcash;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.quickcash.Util.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginViewModel extends AndroidViewModel implements Observable {

        public MutableLiveData<Event<String>> _navigateToDashboard = new MutableLiveData<Event<String>>();

        public LiveData<Event<String>> navigateToDashboard;


    @Bindable
    public String username = "", password= "";

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Event<String>> get_navigateToDashboard() {
        return _navigateToDashboard;
    }

    public LiveData<Event<String>> getNavigateToDashboard() {
        return navigateToDashboard;
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

//        if(validateInfo()){ //If information exists in database
//            _navigateToDashboard.setValue(new Event("123"));
//        }
//
//       else{ //If information does not exist
            String errorMessage = "One or both of your login credentials is incorrect";
            Toast.makeText(getApplication(), errorMessage, Toast.LENGTH_LONG).show(); //show error Message
//        }
    }

    /* TODO: Donovan */

    public boolean validateInfo(){
        //Check firebase to see if this is a valid login
        return true;
    }
}
