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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quickcash.AddTaskViewModel;
import com.example.quickcash.Model.Task;
import com.example.quickcash.Model.User;
import com.example.quickcash.R;
import com.example.quickcash.Util.TaskAdapter;
import com.example.quickcash.databinding.FragmentHelperDashboardBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class ClientDashboardFragment extends Fragment {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    AddTaskViewModel viewModel;
    TaskAdapter taskAdapter;
    FirebaseDatabase db;
    FirebaseRecyclerOptions<Task> options;
    FragmentHelperDashboardBinding binding;
    Query baseQuery;
    FirebaseAuth DBAuth;

    public ClientDashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplication());
        editor = sharedPreferences.edit();
        db = FirebaseDatabase.getInstance();
        DBAuth = FirebaseAuth.getInstance();
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

//        //Navigation to My Profile Page
//        NavDirections actionDashboardToMyProfile= ClientDashboardFragmentDirections.clientDashboardToMyProfile();
//        Button toMyProfileButton = getView().findViewById(R.id.clientMyProfileButton);
//
//        toMyProfileButton.setOnClickListener(v -> Navigation.findNavController(view).navigate(actionDashboardToMyProfile));
//
//
//        //Navigation to Notifications Page
//        NavDirections actionDashboardToNotifications= ClientDashboardFragmentDirections.clientDashboardtoNotification();
//        Button toNotificationsButton = getView().findViewById(R.id.clientNotificationsButton);
//
//        toNotificationsButton.setOnClickListener(v -> Navigation.findNavController(view).navigate(actionDashboardToNotifications));
//
//        //Navigation to Add Tasks Page
//        NavDirections actionDashboardToCreateTasks = ClientDashboardFragmentDirections.clientDashboardToCreateTask();
//        Button goToAddTasksButton =  getView().findViewById(R.id.addTaskButton);
//
//        goToAddTasksButton.setOnClickListener(v -> Navigation.findNavController(view).navigate(actionDashboardToCreateTasks));

        //Logout and navigate to splash page
        NavDirections actionDashboardToLogin = ClientDashboardFragmentDirections.clientDashboardToSplash();
        FloatingActionButton logOutButton = getView().findViewById(R.id.logOutButton);

        logOutButton.setOnClickListener(v -> {
            editor.putBoolean("LOGGED_IN", false);
            editor.apply();
            Navigation.findNavController(view).navigate(actionDashboardToLogin);
        });

        getUserInfoFromDB(DBAuth.getCurrentUser().getUid());


    }

    public void getUserInfoFromDB(String userID) {

        db.getReference("CLIENTS").child(userID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    User user;
                    user = task.getResult().getValue(User.class);
                    editor.putString("USER_EMAIL_KEY", user.getEmail());
                    editor.putString("USER_NAME_KEY", user.getUsername());
                    editor.putFloat("AVERAGE_RATING_KEY", user.getAvgRating());
                    editor.apply();

                }
            }
        });
    }

}
