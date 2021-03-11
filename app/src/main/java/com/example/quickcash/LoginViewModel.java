package com.example.quickcash;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginViewModel extends ViewModel implements Observable {

    public FirebaseAuth DBAuth;
    public FirebaseUser user;

    @Bindable
    public String email = "", password= "";

    @Bindable
    public MutableLiveData<String> toastMessage = new MutableLiveData<String>();
    @Bindable
    public MutableLiveData<Boolean> registrationNavigate = new MutableLiveData<>();
    @Bindable
    public MutableLiveData<Boolean> validLogin = new MutableLiveData<Boolean>();

    Boolean _validLogin = false;


    public LoginViewModel() {
        DBAuth = FirebaseAuth.getInstance();
        user = null;
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
                        toastMessage.setValue("Correct!");
                        validLogin.setValue(true);
                    } else { //email and un did not match in DB
                        toastMessage.setValue("Error! Incorrect email and/or password");
                    }
                }
            });
        }
    }

    public void goToRegistration(){
        registrationNavigate.setValue(true);
    }



    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

}
