package com.example.quickcash.View;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickcash.R;
import com.example.quickcash.RegistrationViewModel;
import com.example.quickcash.databinding.FragmentHelperInformationBinding;


import org.jetbrains.annotations.NotNull;

public class HelperInformationFragment extends Fragment {
    RegistrationViewModel viewModel;
    FragmentHelperInformationBinding binding;

    public HelperInformationFragment() {
        //Required empty constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        binding = FragmentHelperInformationBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        NavDirections actionHelperInfoToLogin = HelperInformationFragmentDirections.helperInfoToLogin();
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                Navigation.findNavController(view).navigate(actionHelperInfoToLogin);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), callback);


        NavDirections actionHelperToRegistration = HelperInformationFragmentDirections.helperToRegistration();

        final Observer<Boolean> registrationObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean register) {
                Navigation.findNavController(view).navigate(actionHelperToRegistration);
            }
        };

        viewModel.navToRegistration.observe(getViewLifecycleOwner(), registrationObserver);

        NavDirections actionHelperToClient = HelperInformationFragmentDirections.helperInfoToClientInfo();

        final Observer<Boolean> clientObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean otherType) {
                Navigation.findNavController(view).navigate(actionHelperToClient);
            }
        };

        viewModel.navToClient.observe(getViewLifecycleOwner(), clientObserver);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}