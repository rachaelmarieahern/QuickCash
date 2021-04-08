package com.example.quickcash.View.Client;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickcash.R;
import com.example.quickcash.SpecificTaskViewModel;
import com.example.quickcash.databinding.FragmentClientSpecificTaskBinding;
import com.example.quickcash.databinding.FragmentSpecificTaskViewHelperBinding;
import com.google.firebase.database.FirebaseDatabase;

public class ClientSpecificTaskFragment extends Fragment {
    SharedPreferences sharedPreferences;
    SpecificTaskViewModel viewModel;
    FragmentClientSpecificTaskBinding binding;
    FirebaseDatabase db;
    SharedPreferences.Editor editor;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        db = FirebaseDatabase.getInstance();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = sharedPreferences.edit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_specific_task_view_helper, container, false);

        binding = FragmentClientSpecificTaskBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(SpecificTaskViewModel.class);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }



    public ClientSpecificTaskFragment() {
        // Required empty public constructor
    }

}