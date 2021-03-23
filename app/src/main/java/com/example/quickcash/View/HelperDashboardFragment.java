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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.quickcash.Model.Task;
import com.example.quickcash.R;
import com.example.quickcash.AddTaskViewModel;
import com.example.quickcash.Util.TaskAdapter;
import com.example.quickcash.databinding.FragmentHelperDashboardBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HelperDashboardFragment extends Fragment {

        AddTaskViewModel viewModel;
        private RecyclerView taskListRecyclerView;
        TaskAdapter taskAdapter;
        FirebaseRecyclerOptions<Task> options;
        FragmentHelperDashboardBinding binding;
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        Query baseQuery;


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
            binding = FragmentHelperDashboardBinding.inflate(inflater, container, false);
            binding.setViewModel(viewModel);
            return binding.getRoot();
        }


        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            baseQuery = FirebaseDatabase.getInstance().getReference().child("TASKS");
            Spinner taskFilter = (Spinner) getView().findViewById(R.id.taskFilteringSpinner);
            taskFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    TextView taskType = (TextView) view;
                    String taskText = taskType.getText().toString();
                    updateRecyclerView(taskText);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //Getting the query from Firebase
            options = new FirebaseRecyclerOptions.Builder<Task>().setLifecycleOwner(getViewLifecycleOwner()).setQuery(baseQuery, Task.class).build();
            //Instantiating the adapter
            taskAdapter = new TaskAdapter(options, getActivity().getApplicationContext(), Navigation.findNavController(view));
            //Finding the recyclerview
            taskListRecyclerView = getView().findViewById(R.id.taskListRecyclerView);
            //Setting the layout of the recyclerview to Linear
            taskListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,true));
            taskListRecyclerView.setHasFixedSize(true);
            //Adding the adapter to the recyclerview
            taskListRecyclerView.setAdapter(taskAdapter);


            //Logout and navigate to splash page
            NavDirections actionDashboardToLogin = HelperDashboardFragmentDirections.helperDashboardToSplash();
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

    public void updateRecyclerView(String taskTypeFilterText){
        //Creating a new query based on the task type selected
        Query newQuery = baseQuery.orderByChild("taskType").equalTo(taskTypeFilterText);
        //Updating the recyclerview adapter with the new query
        FirebaseRecyclerOptions<Task> newOptions = new FirebaseRecyclerOptions.Builder<Task>().setLifecycleOwner(getViewLifecycleOwner()).setQuery(newQuery, Task.class).build();
        taskAdapter.updateOptions(newOptions);
        }

    }