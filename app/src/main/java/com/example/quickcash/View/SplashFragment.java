package com.example.quickcash.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quickcash.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SplashFragment extends Fragment {

    Animation rotateAnimation;
    ImageView imageView;
    FirebaseAuth DBAuth;
    FirebaseUser user;
    boolean loggedIn = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        DBAuth = FirebaseAuth.getInstance();
        user = DBAuth.getCurrentUser();
        //TODO: Set user logged in variable once gotten from Tom

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                redirect(view);
            }
        }, 5000);
    }

    public void redirect(View view) {
        if (loggedIn) {
            NavDirections actionSplashToDashboard = SplashFragmentDirections.splashToDashboard();
            //Navigate to dashboard page
            Navigation.findNavController(view).navigate(actionSplashToDashboard);
        } else {
            NavDirections actionSplashToLogin = SplashFragmentDirections.splashToLogin();
            //Navigate to login page
            Navigation.findNavController(view).navigate(actionSplashToLogin);

        }
    }

    private void rotateAnimation() {
        rotateAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        this.imageView.startAnimation(rotateAnimation);
    }
}