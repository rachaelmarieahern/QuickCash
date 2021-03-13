package com.example.quickcash.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickcash.Model.Task;
import com.example.quickcash.R;
import com.example.quickcash.TaskViewModel;
import com.example.quickcash.Util.TaskAdapter;
import com.example.quickcash.databinding.FragmentDashboardBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashboardFragment extends Fragment {

    TaskViewModel viewModel;
    DatabaseReference databaseReference;
    private RecyclerView taskListRecyclerView;
    TaskAdapter taskAdapter;
    FirebaseRecyclerOptions<Task> options;
    FragmentDashboardBinding binding;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);



//        databaseReference = FirebaseDatabase.getInstance().getReference();
//        //Getting the query from Firebase
//        options = new FirebaseRecyclerOptions.Builder<Task>().setQuery(databaseReference, Task.class).build();
//        //Instaniating the adapter
//        taskAdapter = new TaskAdapter(options);
//        //Finding the recyclerview
//        taskListRecyclerView = view.findViewById(R.id.taskListRecyclerView);
//        //Setting the layout of the recyclerview to Linear
//        taskListRecyclerView.setLayoutManager(
//                new LinearLayoutManager(view.getContext())
//                );
//        //Adding the adapter to the recyclerview
//        taskListRecyclerView.setAdapter(taskAdapter);


        binding =  FragmentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);
        ViewModelStoreOwner store = navController.getViewModelStoreOwner(R.id.nav_graph);
        viewModel = new ViewModelProvider(store, getDefaultViewModelProviderFactory()).get(TaskViewModel.class);

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