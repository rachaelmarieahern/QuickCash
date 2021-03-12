package com.example.quickcash.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.quickcash.Model.User;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";

    public SessionManagement(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(User user) {
        //save session of user whenever user is logged in
    }


    //return user whose session is saved
    public int getSession() {
        return -1;
    }
}
