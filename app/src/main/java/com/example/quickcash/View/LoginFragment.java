package com.example.quickcash.View;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickcash.ViewModel.LoginViewModel;
import com.example.quickcash.R;
import com.example.quickcash.databinding.FragmentLoginBinding;

import org.jetbrains.annotations.NotNull;
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

        final NavController navController = Navigation.findNavController(view);
        viewModel.navController = navController;

    }

    }