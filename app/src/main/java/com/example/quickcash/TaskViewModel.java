package com.example.quickcash;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;

import com.example.quickcash.Util.ErrorTypes;

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
    public List<ErrorTypes> errors = new ArrayList<ErrorTypes>();

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

    public void addTaskToDB(){
        //TODO: Donovon you can add the functionality here to save a task object to firebase (after creating the object class)
    }

    public void getTaskFromDB(int taskID){
        //TODO: Donovon you can add functionality here to get a task object from firebase and store the elements in these variables
    }
}
