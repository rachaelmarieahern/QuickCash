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


        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Observer<String> toastObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newToast) {
                if (!newToast.equals("")) {
                    Toast.makeText(getActivity().getApplicationContext(), newToast, Toast.LENGTH_LONG).show();
                }
            }
        };
        viewModel.toastMessage.observe(getViewLifecycleOwner(), toastObserver);

        NavDirections actionLoginToRegistration = LoginFragmentDirections.loginToRegistration();

        final Observer<Boolean> registrationObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean register) {
                    Navigation.findNavController(view).navigate(actionLoginToRegistration);
            }
        };

      viewModel.registrationNavigate.observe(getViewLifecycleOwner(), registrationObserver);


        NavDirections actionLoginToDashboard = LoginFragmentDirections.loginToDashboard();

        final Observer<Boolean> loginObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean validLogin) {
                    Navigation.findNavController(view).navigate(actionLoginToDashboard);
            }
        };

        viewModel.validLogin.observe(getViewLifecycleOwner(), loginObserver);
    }

}