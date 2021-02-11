package com.example.quickcash.View;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quickcash.LoginViewModel;
import com.example.quickcash.R;
import com.example.quickcash.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment  {

    LoginViewModel viewModel;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        FragmentLoginBinding binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_login);
        binding.setViewModel(viewModel);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //Set up objects for navigation to registration page
        Button registerButton = (Button) getView().findViewById(R.id.registerButton);
        NavDirections actionLoginToRegistration = LoginFragmentDirections.loginToRegister();


        //Navigate to registration page
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(actionLoginToRegistration);
            }
        });
    }

}
