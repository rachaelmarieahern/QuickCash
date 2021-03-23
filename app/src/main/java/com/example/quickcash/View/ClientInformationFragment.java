package com.example.quickcash.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.quickcash.R;
import com.example.quickcash.RegistrationViewModel;
import com.example.quickcash.databinding.FragmentClientInformationBinding;

import org.jetbrains.annotations.NotNull;


public class ClientInformationFragment extends Fragment {
    RegistrationViewModel viewModel;
    NavController navController;
    FragmentClientInformationBinding binding;

    public ClientInformationFragment() {
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
        navController = Navigation.findNavController(requireActivity(), R.id.fragment);
        ViewModelStoreOwner store = navController.getViewModelStoreOwner(R.id.loginGraph);
        viewModel = new ViewModelProvider(store).get(RegistrationViewModel.class);
        binding = FragmentClientInformationBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        NavDirections actionClientToRegistration = ClientInformationFragmentDirections.clientToRegistration();

        final Observer<Boolean> registrationObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean register) {
                Navigation.findNavController(view).navigate(actionClientToRegistration);
            }
        };

        viewModel.navToRegistration.observe(getViewLifecycleOwner(), registrationObserver);

        NavDirections actionClientToHelper = ClientInformationFragmentDirections.clientInfoToHelperInfo();

        final Observer<Boolean> helperObserver = new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean otherType) {
                Navigation.findNavController(view).navigate(actionClientToHelper);
            }
        };

        viewModel.navToHelper.observe(getViewLifecycleOwner(), helperObserver);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }

}
