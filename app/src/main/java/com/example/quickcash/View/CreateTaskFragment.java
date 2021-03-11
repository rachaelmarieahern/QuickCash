package com.example.quickcash.View;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quickcash.R;
import com.example.quickcash.TaskViewModel;
import com.example.quickcash.databinding.FragmentCreateTaskBinding;


public class CreateTaskFragment extends Fragment{
    TaskViewModel viewModel;

    public CreateTaskFragment() {
        //Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        FragmentCreateTaskBinding binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_create_task);
        binding.setViewModel(viewModel);
        final Observer<String> toastObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newToast) {
                Toast.makeText(getActivity().getApplicationContext(), newToast, Toast.LENGTH_LONG).show();

            }
        };
        viewModel.toastMessage.observe(getViewLifecycleOwner(), toastObserver);
        return inflater.inflate(R.layout.fragment_create_task, container, false);
    }
}

