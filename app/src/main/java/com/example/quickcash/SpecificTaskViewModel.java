package com.example.quickcash;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;

import androidx.lifecycle.AndroidViewModel;

public class SpecificTaskViewModel extends AndroidViewModel implements Observable {

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());

        @Bindable
        public final String headLine;
        @Bindable
        public final String description;
        @Bindable
        public final String rawStartDateString;
        @Bindable
        public final String rawEndDateString;
        @Bindable
        public String startDateString;
        @Bindable
        public String endDateString;
        @Bindable
        public final boolean urgent;
        @Bindable
        public String wage = "";

        public SpecificTaskViewModel(Application application){
            super(application);
            description = getString(R.string.DESCRIPTION_KEY, "No Description Found");
            headLine = getString(R.string.HEADLINE_KEY, "No Headline Found");
            wage = getString(R.string.WAGE_KEY, "No Wage Found");
            rawStartDateString = getString(R.string.START_DATE_KEY, "No Start Date Found");
            rawEndDateString = getString(R.string.END_DATE_KEY, "No End Date Found");
            urgent = sharedPreferences.getBoolean("URGENT", false);
        }

        @Override
        public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
            //method is used for navigation
        }

        @Override
        public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
            //method is used for navigation
        }

        public void parseDates() {
            endDateString = rawEndDateString;
            startDateString = rawStartDateString;
        }


        public String getString(int keyID, String defaultString){
            return sharedPreferences.getString(getApplication().getResources().getString(keyID), defaultString);
        }

    }
