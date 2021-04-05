package com.example.quickcash.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RatingBar;
import android.widget.Button;
import com.example.quickcash.OtherProfileViewModel;
import com.example.quickcash.R;
import com.example.quickcash.databinding.FragmentClientProfileBinding;


public class ClientProfileFragment extends Fragment {
    OtherProfileViewModel viewModel;

    public ClientProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_client_profile, container, false);

        viewModel = new ViewModelProvider(this).get(OtherProfileViewModel.class);
        FragmentClientProfileBinding binding = FragmentClientProfileBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RatingBar ratingBar = getView().findViewById(R.id.clientRatingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBar.setIsIndicator(true);
                viewModel.ratingSubmitted(rating);
            }
        });

        Button  payPal = getView().findViewById(R.id.clientPayPalButton);
        NavDirections goToPaypal = ClientProfileFragmentDirections.helperPayPalClient();

        payPal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(goToPaypal);
            }
        });

    }

}