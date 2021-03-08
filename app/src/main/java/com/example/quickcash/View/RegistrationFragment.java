package com.example.quickcash.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.quickcash.databinding.FragmentRegistrationBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickcash.R;
import com.example.quickcash.RegistrationViewModel;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class RegistrationFragment extends Fragment {
    RegistrationViewModel viewModel;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        FragmentRegistrationBinding binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_registration);
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

        NavDirections actionRegisterToDashboard = RegistrationFragmentDirections.registrationToDashboard();

        //Handle Account Status
        viewModel.DBAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth DBAuth) {
                if (viewModel.user != null) {     //If user is logged in, navigate to dashboard page
                    Navigation.findNavController(view).navigate(actionRegisterToDashboard);
                }
            }
        });
    }
}
