package com.example.quickcash.View;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickcash.Model.Task;
import com.example.quickcash.R;
import com.example.quickcash.AddTaskViewModel;
import com.example.quickcash.Util.TaskAdapter;
import com.example.quickcash.databinding.FragmentDashboardBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class HelperDashboardFragment extends Fragment {

        AddTaskViewModel viewModel;
        private RecyclerView taskListRecyclerView;
        TaskAdapter taskAdapter;
        FirebaseRecyclerOptions<Task> options;
        FragmentDashboardBinding binding;
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;

        public HelperDashboardFragment() {
            // Required empty public constructor
        }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplication());
        editor = sharedPreferences.edit();
    }

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            inflater.inflate(R.layout.fragment_helper_dashboard, container, false);

            viewModel = new ViewModelProvider(this).get(AddTaskViewModel.class);
            binding =  FragmentDashboardBinding.inflate(inflater, container, false);
            binding.setViewModel(viewModel);
            return binding.getRoot();
        }


        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            Query query = FirebaseDatabase.getInstance().
                    getReference().child("TASKS");
            //Getting the query from Firebase
            options = new FirebaseRecyclerOptions.Builder<Task>().setLifecycleOwner(getViewLifecycleOwner()).setQuery(query, Task.class).build();
            //Instaniating the adapter
            taskAdapter = new TaskAdapter(options, getActivity().getApplicationContext(), Navigation.findNavController(view));
            //Finding the recyclerview
            taskListRecyclerView = getView().findViewById(R.id.taskListRecyclerView);
            //Setting the layout of the recyclerview to Linear
            taskListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,true));
            taskListRecyclerView.setHasFixedSize(true);
            //Adding the adapter to the recyclerview
            taskListRecyclerView.setAdapter(taskAdapter);



            //Navigation to Add Tasks Page
            NavDirections actionDashboardToCreateTasks = DashboardFragmentDirections.dashboardToCreateTask();
            FloatingActionButton goToAddTasksButton = (FloatingActionButton) getView().findViewById(R.id.goToAddTaskButton);

            goToAddTasksButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(view).navigate(actionDashboardToCreateTasks);
                }
            });

            //Logout and navigate to login page
            NavDirections actionDashboardToLogin = DashboardFragmentDirections.dashboardToLogin();
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