package com.example.quickcash.View;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickcash.AddTaskViewModel;
import com.example.quickcash.R;
import com.example.quickcash.SpecificTaskViewModel;
import com.example.quickcash.databinding.FragmentSpecificTaskViewBinding;

public class SpecificTaskFragment extends Fragment {
    SpecificTaskViewModel viewModel;
    FragmentSpecificTaskViewBinding binding;


    public SpecificTaskFragment() {
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
        binding = FragmentSpecificTaskViewBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(SpecificTaskViewModel.class);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }
}