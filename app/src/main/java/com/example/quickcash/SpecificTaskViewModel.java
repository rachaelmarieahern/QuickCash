package com.example.quickcash;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;

import androidx.lifecycle.AndroidViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;
import java.util.Date;

    public class SpecificTaskViewModel extends AndroidViewModel implements Observable {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());

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
        public double latitude, longitude;
        @Bindable
        public String wage = "";

        public SpecificTaskViewModel(Application application){
            super(application);
            description = getString(R.string.DESCRIPTION_KEY, "No Description Found");
            headLine = getString(R.string.HEADLINE_KEY, "No Headline Found");
            wage = getString(R.string.WAGE_KEY, "No Wage Found");
            startDateString = getString(R.string.START_DATE_KEY, "No Start Date Found");
            endDateString = getString(R.string.END_DATE_KEY, "No End Date Found");
        }

        public void resetValues() {
            headLine = "";
            description = "";
            startDateString = "";
            endDateString = "";
            urgent = false;
            Calendar calendar = Calendar.getInstance();
            calendar.set(2000, 0, 0);
            startDate = calendar.getTime();
            calendar.set(2020, 0, 0);
            endDate = calendar.getTime();
            wage = "0.00";
        }



        @Override
        public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        }

        @Override
        public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        }


        public void parseDates() {

        }


        public String getString(int keyID, String defaultString){
            return sharedPreferences.getString(getApplication().getResources().getString(keyID), defaultString);
        }

        enum TaskType {cleaning, gardening, housework, labour, babysitting}
    }
