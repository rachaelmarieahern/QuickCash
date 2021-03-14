package com.example.quickcash.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        FragmentCreateTaskBinding binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_create_task);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Observer<String> toastObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newToast) {
                Toast.makeText(getActivity().getApplicationContext(), newToast, Toast.LENGTH_LONG).show();

            }
        };
        viewModel.toastMessage.observe(getViewLifecycleOwner(), toastObserver);


        NavDirections actionCreateToDashboard = CreateTaskFragmentDirections.createTaskToDashboard();

        final Observer<Boolean> successObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean success) {
                if(success) {
                    Navigation.findNavController(view).navigate(actionCreateToDashboard);
                }
            }
        };
        viewModel.successfulTask.observe(getViewLifecycleOwner(), successObserver);
    }
}

