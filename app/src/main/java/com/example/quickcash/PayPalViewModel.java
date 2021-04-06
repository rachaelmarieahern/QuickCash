package com.example.quickcash;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;



public class PayPalViewModel extends AndroidViewModel implements Observable {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Bindable
    public MutableLiveData<String> amount = new MutableLiveData<>();
    @Bindable
    public MutableLiveData<Boolean> processPayment = new MutableLiveData<>();

    PayPalViewModel(Application application){
        super(application);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
        editor = sharedPreferences.edit();
        processPayment.setValue(false);
        amount.setValue("0");
    }


    public void payNowClicked(){
        editor.putString("AMOUNT_KEY", amount.getValue());
        processPayment.setValue(true);
    }

    public void getPaidClicked(){
        editor.putString("REQUEST_KEY", amount.getValue());
        processPayment.setValue(true);
    }


    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {

    }
}
