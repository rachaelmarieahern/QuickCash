package com.example.quickcash;
import android.app.Application;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegistrationViewModel extends AndroidViewModel implements Observable {

    @Bindable
    public String username = "", email = "", password= "";
    @Bindable
    public boolean helperSelected = false;
    public RegistrationViewModel(@NonNull Application application) {
        super(application);
    }

    //DB connections
    FirebaseDatabase DB;
    DatabaseReference userTypeRef;
    FirebaseAuth DBAuth;

    enum userType {HELPER, CLIENT}
    enum errorType {invalidUserName, invalidPassword, invalidEmail}
    List<errorType> errors = new ArrayList<errorType>();
    userType userTypeSelection = userType.CLIENT;

    /**
     * When the user clicks the sign up button on the register page
     */
    public void signUpClicked(){
        errors.clear(); //clear error variable
        validateInfo(); //confirm the inputted username, password, and email are correctly formatted
        userTypeSelected(); //save user type in userTypeSelection variable

        if(errors.isEmpty()){ //no errors found!
            registerWithDB(); //add user to DB
        }

        if(!errors.isEmpty()){ //error is found in username, pass, and/or email
            String errorMessage = "";
            if (errors.contains(errorType.invalidUserName)){
                errorMessage = errorMessage.concat("Invalid username");
                username = ""; //reset username
            }
            if (errors.contains(errorType.invalidEmail)){
                errorMessage = errorMessage.concat("\tInvalid email address");
                email = ""; //reset email
            }
            if (errors.contains(errorType.invalidPassword)){
                errorMessage = errorMessage.concat("\tInvalid Password");
                password = ""; //reset password
            }

            Toast.makeText(getApplication(), errorMessage, Toast.LENGTH_LONG).show(); //show error Message
        }

    }

    /**
     * Defines the type of user to create: Client or Helper
     */
    public void userTypeSelected(){
        if (helperSelected) {
            userTypeSelection = userType.HELPER;
        }
        else {
            userTypeSelection = userType.CLIENT;
        }
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

    /**
     * Adds the user to Firebase using username, password, email, and type of user
     */
    public void registerWithDB() {
        DBAuth = FirebaseAuth.getInstance();
        DBAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) { //if adding user to DB was successful
                    DB = FirebaseDatabase.getInstance();
                    userTypeRef = DB.getReference();
                    userTypeRef.child(username).setValue(userTypeSelection.toString());
                    String message = "Welcome User: " + username + " of type " + userTypeSelection.toString() + "\nA welcome email has " +
                            "been sent to " + email;
                    Toast welcome = Toast.makeText(getApplication(), message, Toast.LENGTH_LONG);
                    welcome.show(); //welcome message
                } else {
                    Toast.makeText(getApplication(), "Error! " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
