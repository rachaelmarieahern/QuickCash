package com.example.quickcash;
import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;
import androidx.navigation.NavController;

import com.example.quickcash.Util.ErrorTypes;
import com.example.quickcash.Model.User;
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

    public NavController navController;

    public RegistrationViewModel(@NonNull Application application) {
        super(application);
    }

    //DB connections
    FirebaseDatabase DB;
    DatabaseReference users;
    FirebaseAuth DBAuth;

    enum userType {HELPER, CLIENT}
    List<ErrorTypes> errors = new ArrayList<ErrorTypes>();
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
            if (errors.contains(ErrorTypes.invalidUserName)){
                errorMessage = errorMessage.concat("Invalid username");
                username = ""; //reset username
            }
            if (errors.contains(ErrorTypes.invalidEmail)){
                errorMessage = errorMessage.concat("\tInvalid email address");
                email = ""; //reset email
            }
            if (errors.contains(ErrorTypes.invalidPassword)){
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
            errors.add(ErrorTypes.invalidUserName);
        }

        if (!email.matches("[A-Za-z0-9_]*@[A-Za-z0-9_]*\\.[A-Za-z0-9_/]{1,5}")){
            errors.add(ErrorTypes.invalidEmail);
        }

        if (!password.matches("[A-Za-z0-9_]{6,15}")){
            errors.add(ErrorTypes.invalidPassword);
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
        DBAuth.createUserWithEmailAndPassword(email.trim(), password.trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> createUNPass) {
                if (createUNPass.isSuccessful()) { //if adding user to DB was successful
                    DB = FirebaseDatabase.getInstance();
                    users = DB.getReference("CLIENTS"); //the user is a client
                    if (userTypeSelection.toString().equals("HELPER")) //if the user is a helper
                        users = DB.getReference("HELPERS");
                    //create user in FB auth
                    User newUser = new User (username.trim(), userTypeSelection.toString().trim(), email.trim());
                    users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(newUser)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> setUNType) {
                                    if (setUNType.isSuccessful()) { //if the user is successfully added to FB RT DB
                                        String message = "Welcome User: " + username + " of type " +
                                                userTypeSelection.toString() + "\nA welcome email has " +
                                                "been sent to " + email;
                                        Toast welcome = Toast.makeText(getApplication(), message, Toast.LENGTH_LONG);
                                        welcome.show(); //welcome message
                                        //TODO: Navigate to dashboard from here
                                    } else {
                                        Toast.makeText(getApplication(), "Error! " +
                                                Objects.requireNonNull(setUNType.getException()).getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(getApplication(), "Error! "
                            + Objects.requireNonNull(createUNPass.getException()).getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
