package com.example.quickcash.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.quickcash.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY = "session_user";
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String UserId;

    public SessionManagement(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    //save session of user whenever user is logged in
    public void saveSession(FirebaseUser user) {
        UserId = user.getUid();
        this.user = user;
        editor.putString(SESSION_KEY, UserId).commit();
    }

    //return user whose session is saved
    public String getUId() {
        return UserId;
    }
}
