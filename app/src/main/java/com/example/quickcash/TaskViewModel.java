package com.example.quickcash;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quickcash.Model.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TaskViewModel extends ViewModel implements Observable {

    //DB connections
    public FirebaseDatabase DB;
    public DatabaseReference tasks;
    public FirebaseAuth DBAuth;

    @Bindable
    public String headLine, description;
    @Bindable
    public Date startDate, endDate;
    @Bindable
    public String startDateString, endDateString;
    @Bindable
    public int projectDays, projectHours, projectMinutes;
    @Bindable
    public boolean urgent;
    @Bindable
    public double longitude, latitude;
    @Bindable
    public String wage = "";
    @Bindable
    public MutableLiveData<String> toastMessage = new MutableLiveData<String>();
    @Bindable
    public MutableLiveData<Boolean> successfulTask = new MutableLiveData<>();

    public TaskViewModel(){
        headLine = "";
        description = "";
        startDateString = "";
        endDateString = "";
        urgent = false;
        successfulTask.setValue(false);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000,0,0);
        startDate = calendar.getTime();
        calendar.set(2020,0,0);
        endDate = calendar.getTime();
        wage = "0.00";
    }

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {}

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {}

    enum TaskType{cleaning, gardening, housework, labour, babysitting}
    //UPDATE
    public List<ErrorTypes> errors = new ArrayList<ErrorTypes>();

    /**
     * When the user clicks the sign up button on the register page
     */
    public void addTaskClicked(){
        errors.clear(); //clear error variable
         //confirm the inputted username, password, and email are correctly formatted
        if(validateDateStrings())
            {setDates();}
        validateInfo();


        if(errors.isEmpty()){ //no errors found!
            addTaskToDB(); //add user to DB
        }

        if(!errors.isEmpty()){ //error is found in username, pass, and/or email
            String errorMessage = "";
            if (errors.contains(ErrorTypes.invalidHeadline)){
                errorMessage = errorMessage.concat("\nHeadline must contain at most 40 characters");
            }

            if (errors.contains(ErrorTypes.invalidDescription)){
                errorMessage = errorMessage.concat("\nDescription must contain at least 20 characters");
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

            if (errors.contains(ErrorTypes.invalidStartDateString)) {
                errorMessage = errorMessage.concat("\nPlease enter dd/mm/yyyy format on start date");
            }

            if (errors.contains(ErrorTypes.invalidEndDateString)){
                    errorMessage = errorMessage.concat("\nPlease enter dd/mm/yyyy format on end date");
            }
            errorMessage = errorMessage.substring(1);
          toastMessage.setValue(errorMessage);
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
    }

    public boolean validateDateStrings(){
        boolean valid = true;
        if(!startDateString.matches("^(0?[1-9]|[12][0-9]|3[01])[/\\-](0?[1-9]|1[012])[/\\-]\\d{4}$")){
            errors.add(ErrorTypes.invalidStartDateString);
            valid = false;
        }
        if(!endDateString.matches("(0?[1-9]|[12][0-9]|3[01])[/\\-](0?[1-9]|1[012])[/\\-]\\d{4}$")){
            errors.add(ErrorTypes.invalidEndDateString);
            valid = false;
        }
        return valid;
    }

    public void addTaskToDB(){
        DBAuth = FirebaseAuth.getInstance();
        DBAuth.signInWithEmailAndPassword("helloman@live.com", "sdf234");
        if (DBAuth.getCurrentUser() != null) {
            DB = FirebaseDatabase.getInstance();
            tasks = DB.getReference();
            Task nTask = new Task(headLine.trim(), description.trim(), startDate, endDate, urgent, longitude,
                    latitude, wage.trim(), projectDays, projectHours, projectMinutes);
            tasks.child("TASKS").push().setValue(nTask).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> addTask) {
                    if (addTask.isSuccessful()) { //if the user is successfully added to FB RT DB
                        toastMessage.setValue("Task Successfully added to DB");
                        successfulTask.setValue(true);
                    } else {
                        toastMessage.setValue("Error! " + Objects.requireNonNull(addTask.getException()).getMessage());
                    }
                }
            });
        }
    }

    public void setDates() {
        int startDay = Integer.parseInt(startDateString.substring(0, 2))-1;
        int startMonth = Integer.parseInt(startDateString.substring(3,5))-1;
        int startYear = Integer.parseInt(startDateString.substring(6,10));

        int endDay = Integer.parseInt(endDateString.substring(0, 2))-1;
        int endMonth = Integer.parseInt(endDateString.substring(3,5))-1;
        int endYear = Integer.parseInt(endDateString.substring(6,10));

        Calendar calendar = Calendar.getInstance();
        calendar.set(startYear, startMonth, startDay);
        startDate = calendar.getTime();
        calendar.set(endYear, endMonth, endDay);
        endDate = calendar.getTime();
        }

    public void getTaskFromDB(int taskID){
        //TODO: Donovon you can add functionality here to get a task object from firebase and store the elements in these variables
        DB = FirebaseDatabase.getInstance();
        tasks = DB.getReference("TASKS");
        tasks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
