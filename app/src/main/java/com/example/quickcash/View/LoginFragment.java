package com.example.quickcash.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.quickcash.LoginViewModel;
import com.example.quickcash.R;
import com.example.quickcash.databinding.FragmentLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LoginFragment extends Fragment {

    LoginViewModel viewModel;
    String oldToast = "";

    public LoginFragment() {
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
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        FragmentLoginBinding binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_login);
        binding.setViewModel(viewModel);
        final Observer<String> toastObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newToast) {
                if(!newToast.equals(oldToast)) {
                    Toast.makeText(getActivity().getApplicationContext(), newToast, Toast.LENGTH_LONG).show();
                }
                oldToast = newToast;
            }
        };
        viewModel.toastMessage.observe(getViewLifecycleOwner(), toastObserver);
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button registerButton = (Button) getView().findViewById(R.id.registerButton);
        NavDirections actionLoginToRegistration = LoginFragmentDirections.loginToRegistration();

        View.OnClickListener registerListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(getTag(), "Register clicked");
                Navigation.findNavController(view).navigate(actionLoginToRegistration);
            }
        };

        registerButton.setOnClickListener(registerListener);

        Button loginButton = (Button) getView().findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(getTag(), "login clicked");
                viewModel.validateLogin();
            }
        });

        NavDirections actionLoginToDashboard = LoginFragmentDirections.loginToDashboard();

        //Handle Account Status
         viewModel.DBAuth.addAuthStateListener(DBAuth -> {
             if (viewModel.validLogin) {     //If user is logged in, navigate to dashboard page
                 Navigation.findNavController(view).navigate(actionLoginToDashboard);
             }
         });
    }
}


