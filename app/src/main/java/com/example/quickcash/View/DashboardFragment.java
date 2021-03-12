package com.example.quickcash.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.quickcash.DashboardViewModel;
import com.example.quickcash.LoginViewModel;
import com.example.quickcash.R;
import com.example.quickcash.TaskViewModel;
import com.example.quickcash.databinding.FragmentDashboardBinding;
import com.example.quickcash.databinding.FragmentLoginBinding;

import org.jetbrains.annotations.NotNull;

public class DashboardFragment extends Fragment {

    TaskViewModel viewModel;


    public DashboardFragment() {
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
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);
        ViewModelStoreOwner store = navController.getViewModelStoreOwner(R.id.nav_graph);
        viewModel = new ViewModelProvider(store, getDefaultViewModelProviderFactory()).get(TaskViewModel.class);
        FragmentDashboardBinding binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_dashboard);
        binding.setViewModel(viewModel);
    }



}