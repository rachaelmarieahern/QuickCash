package com.example.quickcash.View.Helpers;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.navigation.NavDirections;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Button;

import com.example.quickcash.Model.Task;
import com.example.quickcash.MyProfileViewModel;
import com.example.quickcash.OtherProfileViewModel;
import com.example.quickcash.R;

import com.example.quickcash.Util.TaskAdapter;
import com.example.quickcash.databinding.FragmentHelperDashboardBinding;
import com.example.quickcash.databinding.FragmentHelperProfileBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class HelperProfileFragment extends Fragment {

    OtherProfileViewModel viewModel;
    TaskAdapter taskAdapter;
    FirebaseDatabase db;
    FirebaseRecyclerOptions<Task> options;
    FragmentHelperDashboardBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Query baseQuery;
    FirebaseAuth DBAuth;

    public HelperProfileFragment() {
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
        inflater.inflate(R.layout.fragment_helper_profile, container, false);
        viewModel = new ViewModelProvider(this).get(OtherProfileViewModel.class);
        FragmentHelperProfileBinding binding = FragmentHelperProfileBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RatingBar ratingBar = getView().findViewById(R.id.helperRatingBar);
        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
            ratingBar1.setIsIndicator(true);
            viewModel.ratingSubmitted(rating);
        });
        baseQuery = FirebaseDatabase.getInstance().getReference().child("TASKS").orderByChild("applicant").equalTo(sharedPreferences.getString("APPLICANT_KEY", ""));

        //Getting the query from Firebase
        options = new FirebaseRecyclerOptions.Builder<Task>().setLifecycleOwner(getViewLifecycleOwner()).setQuery(baseQuery, Task.class).build();
        //Instantiating the adapter
        taskAdapter = new TaskAdapter(options, getActivity().getApplicationContext(), Navigation.findNavController(view), "HelperMyProfile");
        //Finding the recyclerview
        RecyclerView helperRecyclerView = getView().findViewById(R.id.otherHelperRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true);
        linearLayoutManager.setStackFromEnd(true);

        Button payPal = getView().findViewById(R.id.helperPayPalButton);
        NavDirections goToPaypal = HelperProfileFragmentDirections.clientPayPalHelper();

        payPal.setOnClickListener(v -> Navigation.findNavController(view).navigate(goToPaypal));

        //Setting the layout of the recyclerview to Linear
        helperRecyclerView.setLayoutManager(linearLayoutManager);
        helperRecyclerView.setHasFixedSize(true);
        //Adding the adapter to the recyclerview
        helperRecyclerView.setAdapter(taskAdapter);
    }
}