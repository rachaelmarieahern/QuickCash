package com.example.quickcash.View;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quickcash.Model.User;
import com.example.quickcash.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SplashFragment extends Fragment {

    SharedPreferences sharedPreferences;
    Animation rotateAnimation;
    ImageView imageView;
    FirebaseAuth DBAuth;
    FirebaseUser user;
    boolean loggedIn = false;
    boolean client = false;
    View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplication());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = (ImageView) getView().findViewById(R.id.splashlogo);

            rotateAnimation();

            this.view=view;

        DBAuth = FirebaseAuth.getInstance();
        user = DBAuth.getCurrentUser();

        loggedIn = sharedPreferences.getBoolean("LOGGED_IN", false);

        if (user!=null) {
            String uid = user.getUid();

            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference uidRef = rootRef.child("CLIENTS").child(uid);

            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    client = dataSnapshot.exists();
                    Log.d("Testing: ", "client?" + client);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };

            uidRef.addListenerForSingleValueEvent(eventListener);
        }



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                redirect(view);
            }
        }, 3000);
    }


    public void redirect(View view) {
        //TODO Redirect properly
        if (!loggedIn) {
            NavDirections actionSplashToLogin = SplashFragmentDirections.splashToLogin();
            //Navigate to login page
            Navigation.findNavController(view).navigate(actionSplashToLogin);
        } else {
            if (loggedIn && !client) {
                NavDirections actionSplashToHelperDashboard = SplashFragmentDirections.splashToHelperDashboard();
                //Navigate to dashboard page
                Navigation.findNavController(view).navigate(actionSplashToHelperDashboard);
            }
            if (loggedIn && client) {
                NavDirections actionSplashToClientDashboard = SplashFragmentDirections.SplashToClientDashboard();
                //Navigate to dashboard page
                Navigation.findNavController(view).navigate(actionSplashToClientDashboard);
            }
        }
    }

    private void rotateAnimation() {
        rotateAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        this.imageView.startAnimation(rotateAnimation);
    }
}