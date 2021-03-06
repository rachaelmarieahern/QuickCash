package com.example.quickcash;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskViewModel implements Observable {

    @Bindable
    public String headLine, description;
    @Bindable
    public Date startDate, endDate;
    @Bindable
    public Boolean urgent;
    @Bindable
    public double longitude, latitude, price;
    @Bindable
    public int projectHours,projectMinutes, projectDays;
    @Bindable
    public String errorMessage = "";
    
    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {}

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {}

    enum TaskType{computerRepair, yardwork, dogWalking, babysitting, errands}
    //UPDATE
    enum errorType {invalidHeadline, invalidDescription, invalidEmail}
    List<errorType> errors = new ArrayList<errorType>();

    /**
     * When the user clicks the sign up button on the register page
     */
    public void addTaskClicked(){
        //Kept blank for testing
         errorMessage = "";


//        errors.clear(); //clear error variable
//        validateInfo(); //confirm the inputted username, password, and email are correctly formatted
//        userTypeSelected(); //save user type in userTypeSelection variable
//
//        if(errors.isEmpty()){ //no errors found!
//            registerWithDB(); //add user to DB
//        }
//
//        if(!errors.isEmpty()){ //error is found in username, pass, and/or email
//            String errorMessage = "";
//            if (errors.contains(RegistrationViewModel.errorType.invalidUserName)){
//                errorMessage = errorMessage.concat("Invalid username");
//                username = ""; //reset username
//            }
//            if (errors.contains(RegistrationViewModel.errorType.invalidEmail)){
//                errorMessage = errorMessage.concat("\tInvalid email address");
//                email = ""; //reset email
//            }
//            if (errors.contains(RegistrationViewModel.errorType.invalidPassword)){
//                errorMessage = errorMessage.concat("\tInvalid Password");
//                password = ""; //reset password
//            }
//
//            Toast.makeText(getApplication(), errorMessage, Toast.LENGTH_LONG).show(); //show error Message
//        }

    }

    public void validateInfo(){

//        if (!username.matches("[A-Za-z0-9_]{3,15}")){
//            errors.add(errorType.invalidUserName);
//        }
//
//        if (!email.matches("[A-Za-z0-9_]*@[A-Za-z0-9_]*\\.[A-Za-z0-9_/]{1,5}")){
//            errors.add(errorType.invalidEmail);
//        }
//
//        if (!password.matches("[A-Za-z0-9_]{6,15}")){
//            errors.add(errorType.invalidPassword);
//        }
    }
}
