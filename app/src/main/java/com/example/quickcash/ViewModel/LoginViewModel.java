package com.example.quickcash.ViewModel;

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

import com.example.quickcash.R;
import com.example.quickcash.View.LoginFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class LoginViewModel extends AndroidViewModel implements Observable {

    public NavController navController;

    @Bindable
    public String email = "", password= "";
    @Bindable
    public boolean validLogin;

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    //DB connection
    FirebaseAuth DBAuth;

    public void validateLogin() {
        String emailCred = email.trim();
        String passwordCred = password.trim();
        DBAuth = FirebaseAuth.getInstance();

        //if the email or password is not formatted correctly
        if (!emailCred.matches("[A-Za-z0-9_]*@[A-Za-z0-9_]*\\.[A-Za-z0-9_/]{1,5}") ||
                !passwordCred.matches("[A-Za-z0-9_]{6,15}")){
            Toast.makeText(getApplication(), "Error! Your credentials are not formatted correctly",
                    Toast.LENGTH_LONG).show();
        } else {
            DBAuth.signInWithEmailAndPassword(emailCred, passwordCred).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> LoginAttempt) {
                    if (LoginAttempt.isSuccessful()) { //if the email and un match in DB
                        Toast.makeText(getApplication(), "Credentials match Username & Password in DB",
                                Toast.LENGTH_LONG).show();
                        //TODO: Navigate to dashboard from here
                    } else { //email and un did not match in DB
                        Toast.makeText(getApplication(), "Error! Incorrect email and/or password",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
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
