package com.example.quickcash.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quickcash.AddTaskViewModel;
import com.example.quickcash.R;
import com.example.quickcash.databinding.FragmentCreateTaskBinding;


public class CreateTaskFragment extends Fragment{
    AddTaskViewModel viewModel;

    public CreateTaskFragment() {
        //Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflater.inflate(R.layout.fragment_create_task, container, false);
        //Inflate the layout for this fragment
        viewModel = new ViewModelProvider(this).get(AddTaskViewModel.class);
        FragmentCreateTaskBinding binding = FragmentCreateTaskBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner taskFilter = getView().findViewById(R.id.addTaskTypeSpinner);
        taskFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView taskType = (TextView) view;
                viewModel.taskType = taskType.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Observer<String> toastObserver = newToast -> Toast.makeText(getActivity().getApplicationContext(), newToast, Toast.LENGTH_LONG).show();
        viewModel.toastMessage.observe(getViewLifecycleOwner(), toastObserver);


        NavDirections actionCreateToClientDashboard = CreateTaskFragmentDirections.createTaskToClientDashboard();
        final Observer<Boolean> successObserver = success -> {
            if(success) {
                Navigation.findNavController(view).navigate(actionCreateToClientDashboard);
            }
        };
        viewModel.successfulTask.observe(getViewLifecycleOwner(), successObserver);
    }
}

