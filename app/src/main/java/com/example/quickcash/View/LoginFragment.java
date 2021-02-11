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
import android.widget.Toast;

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
        Button registerButton = getView().findViewById(R.id.registerButton);
        NavDirections actionLoginToRegistration = LoginFragmentDirections.loginToRegister();

        Button dashboardButton = getView().findViewById(R.id.loginGoToDashboardButton);
        NavDirections actionLoginToDashboard = LoginFragmentDirections.loginToDashboard();

        Button loginButton = getView().findViewById(R.id.loginButton);

        //Validate login and allow navigation to dashboard
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                viewModel.validateLogin();
                if(viewModel.validLogin){
                        Toast.makeText(getActivity(), "Valid Login" +viewModel.validLogin, Toast.LENGTH_LONG).show();
                        //ideally navigate to dashboard
                }
                if(!viewModel.validLogin){
                    Toast.makeText(getActivity(), "Invalid Login" + viewModel.validLogin, Toast.LENGTH_LONG).show();
                }
            }
        });

        //Navigate to dashboard page
        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(viewModel.validLogin) {
                    //Was setting button to gray and unclickable originally, and then setting to green and clickable here
                    Navigation.findNavController(view).navigate(actionLoginToDashboard);
                //}
            }
        });

        //Navigate to registration page
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(actionLoginToRegistration);
            }
        });
    }

}
