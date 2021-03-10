package com.example.quickcash;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.quickcash.R;
import com.example.quickcash.View.LoginFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class LoginViewModel extends ViewModel implements Observable {

    public FirebaseAuth DBAuth;
    public FirebaseUser user;
    public boolean validLogin;

    @Bindable
    public String email = "", password= "";
    @Bindable
    public MutableLiveData<String> toastMessage = new MutableLiveData<String>();


    public LoginViewModel() {
        DBAuth = FirebaseAuth.getInstance();
        user = null;
        toastMessage.setValue("");
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
            DBAuth.signInWithEmailAndPassword(emailCred, passwordCred).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> LoginAttempt) {
                    if (LoginAttempt.isSuccessful()) { //if the email and un match in DB
                        validLogin = true;
                        user = DBAuth.getCurrentUser();
                    } else { //email and un did not match in DB
                        toastMessage.setValue("Error! Incorrect email and/or password");
                    }
                }
            });
        }
    }


    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

}
