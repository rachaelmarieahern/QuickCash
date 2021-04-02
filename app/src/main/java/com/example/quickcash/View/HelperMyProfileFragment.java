package com.example.quickcash.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.example.quickcash.MyProfileViewModel;
import com.example.quickcash.R;
import com.example.quickcash.databinding.FragmentClientMyProfileBinding;
import com.example.quickcash.databinding.FragmentHelperMyProfileBinding;

public class HelperMyProfileFragment extends Fragment {

    MyProfileViewModel viewModel;


    public HelperMyProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_helper_my_profile, container, false);
        viewModel = new ViewModelProvider(this).get(MyProfileViewModel.class);
        FragmentHelperMyProfileBinding binding = FragmentHelperMyProfileBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}