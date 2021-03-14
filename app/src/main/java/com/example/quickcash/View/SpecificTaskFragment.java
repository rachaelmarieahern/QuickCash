package com.example.quickcash.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickcash.R;
import com.example.quickcash.TaskViewModel;
import com.example.quickcash.databinding.FragmentSpecificTaskViewBinding;

public class SpecificTaskFragment extends Fragment {
    TaskViewModel viewModel;
    FragmentSpecificTaskViewBinding binding;


    public SpecificTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        binding.setViewModel(viewModel);

        return inflater.inflate(R.layout.fragment_specific_task_view, container, false);
    }
}