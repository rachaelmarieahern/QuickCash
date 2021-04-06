package com.example.quickcash.View.Client;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quickcash.Model.User;
import com.example.quickcash.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

public class ClientNotificationFragment extends Fragment {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    FirebaseDatabase db;


    public ClientNotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = sharedPreferences.edit();
        db = FirebaseDatabase.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_client_notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavDirections actionNotificationToHelperProfile = ClientNotificationFragmentDirections.clientNotificationToHelperProfile();
        Button toHelperProfileButton = getView().findViewById(R.id.clientToHelperProfileButton);

        toHelperProfileButton.setOnClickListener(v -> Navigation.findNavController(view).navigate(actionNotificationToHelperProfile));
        //TODO: ADD APPLICANT ID FROM NOTIFICATION
        getUserInfoFromDB(sharedPreferences.getString("APPLICANT_KEY", "vY7fiWHThBcdps4YUItfE1ROrIt1"));

    }

    public void getUserInfoFromDB(String userID) {

        db.getReference("HELPERS").child(userID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    User user;
                    user = task.getResult().getValue(User.class);
                    editor.putFloat("SUM_OF_RATINGS", (float) user.getSumOfRatings());
                    editor.putInt("NUM_OF_RATINGS", user.getNumOfRatings());
                    editor.apply();
                }
            }
        });
    }

}