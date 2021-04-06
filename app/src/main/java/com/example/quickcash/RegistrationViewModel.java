package com.example.quickcash;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.quickcash.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.installations.InstallationTokenResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.lifecycle.AndroidViewModel;
public class RegistrationViewModel extends AndroidViewModel implements Observable {
    //DB connections
    FirebaseDatabase DB;
    DatabaseReference users;
    public FirebaseAuth DBAuth;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;



    @Bindable
    public String username = "", email = "", password= "";
    @Bindable
    public boolean helperSelected = false;
    public MutableLiveData<String> toastMessage = new MutableLiveData<>();
    @Bindable
    public MutableLiveData<Boolean> validLogin = new MutableLiveData<>();

    @Bindable
    public MutableLiveData<String> userTypeMessage = new MutableLiveData<String>();
    @Bindable
    public MutableLiveData<Boolean> navToRegistration = new MutableLiveData<Boolean>();
    @Bindable
    public MutableLiveData<Boolean> navToClient = new MutableLiveData<Boolean>();
    @Bindable
    public MutableLiveData<Boolean> navToHelper = new MutableLiveData<Boolean>();

    public RegistrationViewModel(Application application) {
        super(application);
        DBAuth = FirebaseAuth.getInstance();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
        editor = sharedPreferences.edit();
        userTypeMessage.setValue(sharedPreferences.getString("USER_TYPE", ""));
    }

    enum userType {HELPER, CLIENT}
    List<ErrorTypes> errors = new ArrayList<>();
    userType userTypeSelection = userType.CLIENT;

    /**
     * When the user clicks the sign up button on the register page
     */
    public void signUpClicked(){
        errors.clear(); //clear error variable
        validateInfo(); //confirm the inputted username, password, and email are correctly formatted

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

            //toastMessage.setValue(errorMessage);
        }

    }

    /**
     * Defines the type of user to create: Client or Helper
     */
    public void typeSelected(boolean helper){
        if (helper) {
            userTypeSelection = userType.HELPER;
            editor.putString("USER_TYPE", "You are registering as a " + userTypeSelection.toString() + ".\nClick the back button to change your user type.");
        }
        else {
            userTypeSelection = userType.CLIENT;
            editor.putString("USER_TYPE", "You are registering as a " + userTypeSelection.toString() + ".\nClick the back button to change your user type.");
        }
        editor.apply();
        navToRegistration.setValue(true);
    }

    public void clientToHelperSelected(){
            navToHelper.setValue(true);
    }


    public void helperToClientSelected(){
        navToClient.setValue(true);
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
                    String uID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    User newUser = new User (username.trim(), userTypeSelection.toString().trim(), email.trim(), "token");
                    users.child(uID).setValue(newUser).addOnCompleteListener(setUNType -> {
                        if (setUNType.isSuccessful()) { //if the user is successfully added to FB RT DB
                            FirebaseInstallations.getInstance().getToken(true).addOnCompleteListener(task -> {
                                String token = task.getResult().getToken();
                                users.child(uID).child("token").setValue(token);
                            });//if the user is successfully added to FB RT DB
                            editor.putBoolean("LOGGED_IN", true);
                            editor.apply();
                            validLogin.setValue(true);
                        } else {
                            toastMessage.setValue( "Error! " + Objects.requireNonNull(setUNType.getException()).getMessage());
                        }
                    });
                } else {
                    toastMessage.setValue( "Error! " + Objects.requireNonNull(createUNPass.getException()).getMessage());
                }
            }
        });
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

}

