package com.example.quickcash;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;

import com.example.quickcash.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

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

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
}