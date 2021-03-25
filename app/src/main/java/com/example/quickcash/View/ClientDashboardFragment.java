package com.example.quickcash.View;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quickcash.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ClientDashboardFragment extends Fragment {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    public ClientDashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplication());
        editor = sharedPreferences.edit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Navigation to My Profile Page
        NavDirections actionDashboardToMyProfile= ClientDashboardFragmentDirections.clientDashboardToMyProfile();
        Button toMyProfileButton = getView().findViewById(R.id.clientMyProfileButton);

        toMyProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(actionDashboardToMyProfile);
            }
        });


        //Navigation to Task Detail page
        //TODO: Make this work through an adapter instead of a button
        NavDirections actionDashboardToTaskDetail= ClientDashboardFragmentDirections.clientDashboardToTaskDetail();
        Button toTaskDetailsButton = getView().findViewById(R.id.clientTaskDetailButton);

        toTaskDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(actionDashboardToTaskDetail);
            }
        });

        //Navigation to Notifications Page
        NavDirections actionDashboardToNotifications= ClientDashboardFragmentDirections.clientDashboardtoNotification();
        Button toNotificationsButton = getView().findViewById(R.id.clientNotificationsButton);

        toNotificationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(actionDashboardToNotifications);
            }
        });

        //Navigation to Add Tasks Page
        NavDirections actionDashboardToCreateTasks = ClientDashboardFragmentDirections.clientDashboardToCreateTask();
        Button goToAddTasksButton =  getView().findViewById(R.id.addTaskButton);

        goToAddTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(actionDashboardToCreateTasks);
            }
        });

        //Logout and navigate to splash page
        NavDirections actionDashboardToLogin = ClientDashboardFragmentDirections.clientDashboardToSplash();
        FloatingActionButton logOutButton = (FloatingActionButton) getView().findViewById(R.id.logOutButton);

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("LOGGED_IN", false);
                editor.apply();
                Navigation.findNavController(view).navigate(actionDashboardToLogin);
            }
        });
    }

}