package com.example.quickcash;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

public class OtherProfileViewModel extends AndroidViewModel implements Observable {

    DatabaseReference db;
    FirebaseAuth dbAuth;
    SharedPreferences sharedPreferences;

    @Bindable
    private double avgRating, sumOfRatings;
    private String userID, userType;
    private boolean userTypeBoolean;
    private int numOfRatings = 0;

    OtherProfileViewModel(Application application){
        super(application);
        dbAuth = FirebaseAuth.getInstance();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
        userID=sharedPreferences.getString("AUTHOR_KEY", "");
        userTypeBoolean=!(sharedPreferences.getBoolean("USER_TYPE_KEY", false));

        if (userTypeBoolean){
            userType = "CLIENT";
        }
        else userType = "HELPER";
    }

    public void ratingSubmitted(double currentRating){
        numOfRatings++;
        sumOfRatings += currentRating;
        avgRating = (sumOfRatings/numOfRatings);
        HashMap<String, Object> update = new HashMap<>();
        update.put("avgRating", avgRating);

        db.child(userType).child(userID).updateChildren(update);
    }


    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
}