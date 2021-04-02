package com.example.quickcash;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class OtherProfileViewModel extends AndroidViewModel implements Observable {

    FirebaseDatabase db;
    FirebaseAuth dbAuth;
    DatabaseReference dbRef;
    SharedPreferences sharedPreferences;

    @Bindable
    private double avgRating, sumOfRatings;
    private String userID, userType;
    private boolean userTypeBoolean;
    private int numOfRatings;

    public OtherProfileViewModel(Application application){
        super(application);
        dbAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
        userTypeBoolean=sharedPreferences.getBoolean("USER_TYPE_KEY", true);

        if (!userTypeBoolean){
            userType = "CLIENTS";
            userID = sharedPreferences.getString("AUTHOR_KEY", "");
        }
        else {userType = "HELPERS";
            userID = sharedPreferences.getString("APPLICANT_KEY", "vY7fiWHThBcdps4YUItfE1ROrIt1");
             }


        sumOfRatings = sharedPreferences.getFloat("SUM_OF_RATINGS", 0);
        numOfRatings = sharedPreferences.getInt("NUM_OF_RATINGS", 0);
    }

    public void ratingSubmitted(double currentRating){
        numOfRatings++;
        sumOfRatings += currentRating;
        avgRating = (sumOfRatings/numOfRatings);
        Log.d("Rating",  avgRating + " to be put on" + userType + " " + userID);
        dbRef.child(userType).child(userID).child("avgRating").setValue(avgRating);
        dbRef.child(userType).child(userID).child("sumOfRatings").setValue(sumOfRatings);
        dbRef.child(userType).child(userID).child("numOfRatings").setValue(numOfRatings);
    }


    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
}