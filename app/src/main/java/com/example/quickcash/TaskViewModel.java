package com.example.quickcash;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.quickcash.Util.ErrorTypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskViewModel extends ViewModel implements Observable {

    @Bindable
    public String headLine, description;
    @Bindable
    public Date startDate, endDate;
    @Bindable
    public boolean urgent;
    @Bindable
    public int longitude, latitude;
    @Bindable
    public String wage = "";
    @Bindable
    public int projectHours,projectMinutes, projectDays;
    @Bindable
    public MutableLiveData<String> toastMessage = new MutableLiveData<String>();

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
        errors.clear(); //clear error variable
        validateInfo(); //confirm the inputted username, password, and email are correctly formatted

        if(errors.isEmpty()){ //no errors found!
            addTaskToDB(); //add user to DB
        }

        if(!errors.isEmpty()){ //error is found in username, pass, and/or email
            String errorMessage = "";
            if (errors.contains(ErrorTypes.invalidHeadline)){
                errorMessage = errorMessage.concat("\nHeadline contains too many characters!");
            }

            if (errors.contains(ErrorTypes.invalidDescription)){
                errorMessage = errorMessage.concat("\nDescription contains too few characters!");
            }

            if (errors.contains(ErrorTypes.invalidWage)){
                errorMessage = errorMessage.concat("\nWage must be greater than 0");
            }

            if (errors.contains(ErrorTypes.requiredFieldsBlank)){
                errorMessage = errorMessage.concat("\nAll fields are required");
            }

            if (errors.contains(ErrorTypes.invalidDateRange)){
                errorMessage = errorMessage.concat("\nEnd date must be after start date");
            }
           //toastMessage.setValue(errorMessage);
        }
    }

    public void validateInfo(){

        if (headLine.length()>41){
            errors.add(ErrorTypes.invalidHeadline);
        }
        if (description.length()<20){
            errors.add(ErrorTypes.invalidDescription);
        }
        if (startDate.after(endDate)){
            errors.add(ErrorTypes.invalidDateRange);
        }
        if (Double.parseDouble(wage) <= 0){
            errors.add(ErrorTypes.invalidWage);
        }

        if (headLine.length()==0 ||  wage.length()==0){
            errors.add(ErrorTypes.requiredFieldsBlank);
        }

        if(startDate == null || endDate == null){
            errors.add(ErrorTypes.datesBlank);
        }
    }

    public void addTaskToDB(){
        //TODO: Donovon you can add the functionality here to save a task object to firebase (after creating the object class)
    }

    public void getTaskFromDB(int taskID){
        //TODO: Donovon you can add functionality here to get a task object from firebase and store the elements in these variables
    }
}