package com.example.quickcash;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginViewModel extends AndroidViewModel implements Observable {

    private final FirebaseAuth DBAuth;
    private final SharedPreferences.Editor editor;



    @Bindable
    public String email = "";
    @Bindable
    public String password= "";

    @Bindable
    public MutableLiveData<String> toastMessage = new MutableLiveData<>();
    @Bindable
    public MutableLiveData<Boolean> navToInfo = new MutableLiveData<>();
    @Bindable
    public MutableLiveData<Boolean> validLogin = new MutableLiveData<>();


    public LoginViewModel(Application application) {
        super(application);
        DBAuth = FirebaseAuth.getInstance();
        FirebaseUser user = null;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
        editor = sharedPreferences.edit();
    }

    //DB connection


    public void validateLogin() {
        String emailCred = email.trim();
        String passwordCred = password.trim();

        //if the email or password is not formatted correctly
        if (!emailCred.matches("[A-Za-z0-9_]*@[A-Za-z0-9_]*\\.[A-Za-z0-9_/]{1,5}") ||
                !passwordCred.matches("[A-Za-z0-9_]{6,15}")){
            toastMessage.setValue("Error! Your credentials are not formatted correctly");
          } else {
            DBAuth.signInWithEmailAndPassword(emailCred, passwordCred).addOnCompleteListener(LoginAttempt -> {
                if (LoginAttempt.isSuccessful()) { //if the email and un match in DB
                    editor.putBoolean("LOGGED_IN", true);
                    editor.apply();
                    validLogin.setValue(true);
                } else { //email and un did not match in DB
                    toastMessage.setValue("Error! Incorrect email and/or password");
                }
            });
        }
    }

    public void goToRegistration(){
        navToInfo.setValue(true);
    }



    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

}
