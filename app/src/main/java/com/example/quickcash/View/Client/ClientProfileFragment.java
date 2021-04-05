package com.example.quickcash.View.Client;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;

import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.RatingBar;
import android.widget.Button;
import com.example.quickcash.OtherProfileViewModel;
import com.example.quickcash.R;

import com.example.quickcash.Model.Task;
import com.example.quickcash.MyProfileViewModel;
import com.example.quickcash.OtherProfileViewModel;
import com.example.quickcash.R;
import com.example.quickcash.Util.TaskAdapter;
import com.example.quickcash.databinding.FragmentClientMyProfileBinding;

import com.example.quickcash.databinding.FragmentClientProfileBinding;
import com.example.quickcash.databinding.FragmentHelperDashboardBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class ClientProfileFragment extends Fragment {
    OtherProfileViewModel viewModel;
    Query baseQuery;
    TaskAdapter taskAdapter;
    FirebaseDatabase db;
    FirebaseRecyclerOptions<Task> options;
    FragmentHelperDashboardBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    FirebaseAuth DBAuth;

    public ClientProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
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


            baseQuery = FirebaseDatabase.getInstance().getReference().child("TASKS").orderByChild("author").
                    equalTo(sharedPreferences.getString("AUTHOR_KEY", ""));

            //Getting the query from Firebase
            options = new FirebaseRecyclerOptions.Builder<Task>().setLifecycleOwner(getViewLifecycleOwner()).setQuery(baseQuery, Task.class).build();
            //Instantiating the adapter
            taskAdapter = new TaskAdapter(options, getActivity().getApplicationContext(),
                    Navigation.findNavController(view), "ClientMyProfile");
            //Finding the recyclerview
            RecyclerView clientRecyclerView = getView().findViewById(R.id.otherClientRecyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.VERTICAL, true);
            linearLayoutManager.setStackFromEnd(true);

            //Setting the layout of the recyclerview to Linear
            clientRecyclerView.setLayoutManager(linearLayoutManager);
            clientRecyclerView.setHasFixedSize(true);
            //Adding the adapter to the recyclerview
            clientRecyclerView.setAdapter(taskAdapter);
        }

    }
