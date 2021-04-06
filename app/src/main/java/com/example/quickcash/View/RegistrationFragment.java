package com.example.quickcash.View;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.quickcash.Util.SessionManagement;
import com.example.quickcash.databinding.FragmentRegistrationBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quickcash.RegistrationViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class RegistrationFragment extends Fragment {
    RegistrationViewModel viewModel;
    FirebaseAuth DBAuth;
    FirebaseUser userLoggedIn;
    SessionManagement session;
    NavController navController;

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
        viewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        FragmentRegistrationBinding binding = FragmentRegistrationBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);

        final Observer<String> toastObserver = newToast -> {
            if (!newToast.equals(""))
            Toast.makeText(getActivity().getApplicationContext(), newToast,Toast.LENGTH_LONG).show();
        };
        viewModel.toastMessage.observe(getViewLifecycleOwner(), toastObserver);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavDirections actionRegisterToClientInfo = RegistrationFragmentDirections.registrationToClientInfo();
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(view).navigate(actionRegisterToClientInfo);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);

        NavDirections actionRegisterToDashboard = RegistrationFragmentDirections.registrationToSplash();


        final Observer<Boolean> validObserver = validLogin -> {
            createSession();
            Navigation.findNavController(view).navigate(actionRegisterToDashboard);
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