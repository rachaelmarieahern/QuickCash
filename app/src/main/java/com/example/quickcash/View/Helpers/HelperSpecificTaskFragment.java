package com.example.quickcash.View.Helpers;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quickcash.Model.Application;

import com.example.quickcash.Model.User;
import com.example.quickcash.R;
import com.example.quickcash.SpecificTaskViewModel;
import com.example.quickcash.databinding.FragmentSpecificTaskViewHelperBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HelperSpecificTaskFragment extends Fragment {
    SharedPreferences sharedPreferences;
    SpecificTaskViewModel viewModel;
    FragmentSpecificTaskViewHelperBinding binding;
    FirebaseDatabase db;
    SharedPreferences.Editor editor;


    public HelperSpecificTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        db = FirebaseDatabase.getInstance();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = sharedPreferences.edit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_specific_task_view_helper, container, false);

        binding = FragmentSpecificTaskViewHelperBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(SpecificTaskViewModel.class);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button apply = getView().findViewById(R.id.ApplyForTaskBtn);
        apply.setOnClickListener(v -> {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            FirebaseAuth DBAuth = FirebaseAuth.getInstance();
            if (DBAuth.getCurrentUser() != null) {
                FirebaseDatabase DB = FirebaseDatabase.getInstance();
                DatabaseReference applications = DB.getReference();
                String taskID = sharedPreferences.getString("taskDatabaseID", "NO TASK DB FOUND");
                String UserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                Application newApplication = new Application(UserId, "IN PROGRESS", taskID);
                applications.child("TASKAPPLICATIONS").push().setValue(newApplication).addOnCompleteListener(addApplication -> {
                    if (addApplication.isSuccessful()) { //if the task is successfully applied for
                        apply.setEnabled(false);
                    }
                    DB.getReference("TASKS").child(taskID).child("applicant").setValue(UserId);
                });
            }
        });

        NavDirections actionTaskDetailToClientProfile = HelperSpecificTaskFragmentDirections.helperTaskDetailToClientProfile();
        Button toClientProfileButton = getView().findViewById(R.id.helperToClientProfileButton);

        toClientProfileButton.setOnClickListener(v -> Navigation.findNavController(view).navigate(actionTaskDetailToClientProfile));
        getUserInfoFromDB(sharedPreferences.getString("AUTHOR_KEY", ""));
    }

    public void getUserInfoFromDB(String userID) {

        db.getReference("CLIENTS").child(userID).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                User user;
                user = task.getResult().getValue(User.class);
                editor.putFloat("SUM_OF_RATINGS", user.getSumOfRatings());
                editor.putInt("NUM_OF_RATINGS", user.getNumOfRatings());
                editor.apply();
            }
        });
    }

}