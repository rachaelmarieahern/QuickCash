package com.example.quickcash.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.quickcash.LoginViewModel;
import com.example.quickcash.Util.SessionManagement;
import com.example.quickcash.databinding.FragmentDashboardBinding;
import com.example.quickcash.databinding.FragmentRegistrationBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quickcash.R;
import com.example.quickcash.RegistrationViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class RegistrationFragment extends Fragment {
    RegistrationViewModel viewModel;
    FirebaseAuth DBAuth;
    FirebaseUser userLoggedIn;
    SessionManagement session;

    public SessionManagement getSession() {
        return session;
    }

    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_registration, container, false);

        viewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        FragmentRegistrationBinding binding =FragmentRegistrationBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);

        final Observer<String> toastObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newToast) {
                if (!newToast.equals(""))
                Toast.makeText(getActivity().getApplicationContext(), newToast,Toast.LENGTH_LONG).show();
            }
        };
        viewModel.toastMessage.observe(getViewLifecycleOwner(), toastObserver);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavDirections actionRegisterToDashboard = RegistrationFragmentDirections.registrationToDashboard();


        final Observer<Boolean> validObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean validLogin) {
                createSession();
                Navigation.findNavController(view).navigate(actionRegisterToDashboard);
            }
        };

        viewModel.validLogin.observe(getViewLifecycleOwner(), validObserver);
    }

    public void createSession() {
        DBAuth = FirebaseAuth.getInstance();
        userLoggedIn = DBAuth.getCurrentUser();
        session = new SessionManagement(getActivity().getApplicationContext());
        session.saveSession(userLoggedIn);
    }
}