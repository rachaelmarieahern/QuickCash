package com.example.quickcash.View;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DashboardFragment extends Fragment {

        AddTaskViewModel viewModel;
        private RecyclerView taskListRecyclerView;
        TaskAdapter taskAdapter;
        FirebaseRecyclerOptions<Task> options;
        FragmentDashboardBinding binding;

        public DashboardFragment() {
            // Required empty public constructor
        }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            inflater.inflate(R.layout.fragment_dashboard, container, false);

            binding =  FragmentDashboardBinding.inflate(inflater, container, false);
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
            taskListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            taskListRecyclerView.setHasFixedSize(true);
            //Adding the adapter to the recyclerview
            taskListRecyclerView.setAdapter(taskAdapter);

            viewModel = new ViewModelProvider(this).get(AddTaskViewModel.class);

            binding.setViewModel(viewModel);

            //Navigation to Add Tasjs Page
            NavDirections actionDashboardToCreateTasks = DashboardFragmentDirections.dashboardToCreateTask();
            FloatingActionButton goToAddTasksButton = (FloatingActionButton) getView().findViewById(R.id.goToAddTaskButton);

            goToAddTasksButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(view).navigate(actionDashboardToCreateTasks);
                }
            });
        }

    }