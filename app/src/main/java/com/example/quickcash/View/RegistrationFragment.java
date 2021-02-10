package com.example.quickcash.View;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.NavGraphViewModelLazyKt;
import androidx.navigation.Navigation;

import com.example.quickcash.databinding.FragmentRegistrationBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quickcash.R;
import com.example.quickcash.RegistrationViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class RegistrationFragment extends Fragment {
    RegistrationViewModel viewModel;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        FragmentRegistrationBinding binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_registration);
        binding.setViewModel(viewModel);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

    }
}
