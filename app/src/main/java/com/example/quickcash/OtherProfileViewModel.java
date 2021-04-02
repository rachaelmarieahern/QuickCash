package com.example.quickcash;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.ViewModel;

public class OtherProfileViewModel extends ViewModel implements Observable {

    @Bindable
    private double avgRating, sumOfRatings;
    private String userID;
    private int numOfRatings = 0;

    OtherProfileViewModel(){
        //Get user num of ratings
        //Get user?
    }

    public void ratingSubmitted(double currentRating){
        numOfRatings++;
        sumOfRatings += currentRating;
        avgRating = (sumOfRatings/numOfRatings);
    }


    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
}