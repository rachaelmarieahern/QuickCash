package com.example.quickcash;
import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class RegistrationViewModel extends AndroidViewModel implements Observable {

    @Bindable
    public String username = "", email = "", password= "";
    @Bindable
    public boolean helperSelected = false;
    public RegistrationViewModel(@NonNull Application application) {
        super(application);
    }


    enum userType {HELPER, CLIENT}
    enum errorType {invalidUserName, invalidPassword, invalidEmail}
    List<errorType> errors = new ArrayList<errorType>();
    userType userTypeSelection;

    public void signUpClicked(){
        errors.clear();
        validateInfo();
        userTypeSelected();

        if(errors.isEmpty()){
            String message = "Welcome User: " + username + " of type " + userTypeSelection.toString() + "\nA welcome email has " +
                    "been sent to " + email;
            Toast.makeText(getApplication(), message, Toast.LENGTH_LONG).show();
        }

        if(!errors.isEmpty()){
            String errorMessage = "";
            if (errors.contains(errorType.invalidUserName)){
                errorMessage = errorMessage.concat("Invalid username");
                username = "";
            }
            if (errors.contains(errorType.invalidEmail)){
                errorMessage = errorMessage.concat("\tInvalid email address");
                email = "";
            }
            if (errors.contains(errorType.invalidPassword)){
                errorMessage = errorMessage.concat("\tInvalid Password");
                password = "";
            }

            Toast.makeText(getApplication(), errorMessage, Toast.LENGTH_LONG).show();
        }

    }


    public void userTypeSelected(){
        if (helperSelected)
        {userTypeSelection = userType.HELPER;}
        else {userTypeSelection = userType.CLIENT; }
    }

    public void validateInfo(){

        if (!username.matches("[A-Za-z0-9_]{3,15}")){
            errors.add(errorType.invalidUserName);
        }

        if (!email.matches("[A-Za-z0-9_]*@[A-Za-z0-9_]*\\.[A-Za-z0-9_/]{1,5}")){
            errors.add(errorType.invalidEmail);
        }

        if (!password.matches("[A-Za-z0-9_]{3,15}")){
            errors.add(errorType.invalidPassword);
        }
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
}
