package com.example.quickcash;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;


public class MyProfileViewModel extends AndroidViewModel implements Observable {

    SharedPreferences sharedPreferences;

    @Bindable
    public String userEmail;
    @Bindable
    public String userName;
    @Bindable
    public Float avgRating;
    @Bindable
    public String ratingMessage;


    public MyProfileViewModel(Application application) {
        super(application);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
        userName = sharedPreferences.getString("USER_NAME_KEY", "n/a");
        userEmail = sharedPreferences.getString("USER_EMAIL_KEY", "n/a");
        avgRating = sharedPreferences.getFloat("AVERAGE_RATING_KEY", (float) 5.0);

        ratingMessage = "Rating: " + avgRating + "/5";
        }


    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        //method is used for navigation
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        //method is used for navigation
    }
}