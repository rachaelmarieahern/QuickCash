package com.example.quickcash.View;

import android.os.Bundle;
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
import android.widget.Toast;
import com.example.quickcash.LoginViewModel;
import com.example.quickcash.R;
import com.example.quickcash.Util.SessionManagement;
import com.example.quickcash.databinding.FragmentLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class LoginFragment extends Fragment {

    LoginViewModel viewModel;
    FirebaseAuth DBAuth;
    FirebaseUser userLoggedIn;
    SessionManagement session;

    public SessionManagement getSession() {
        return session;
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_login, container, false);
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        FragmentLoginBinding binding = FragmentLoginBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NotNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Observer<String> toastObserver = newToast -> {
            if (!newToast.equals("")) {
                Toast.makeText(getActivity().getApplicationContext(), newToast, Toast.LENGTH_LONG).show();
            }
        };
        viewModel.toastMessage.observe(getViewLifecycleOwner(), toastObserver);

        NavDirections actionLoginToRegistration = LoginFragmentDirections.loginToRegister();

        final Observer<Boolean> registrationObserver = register -> Navigation.findNavController(view).navigate(actionLoginToRegistration);

        viewModel.registrationNavigate.observe(getViewLifecycleOwner(), registrationObserver);


        NavDirections actionLoginToDashboard = LoginFragmentDirections.loginToSplash();

        final Observer<Boolean> loginObserver = validLogin -> {
            createSession();
            Navigation.findNavController(view).navigate(actionLoginToDashboard);
   SessionManagement sessionManagement = new SessionManagement(getActivity().getApplicationContext());
        };
        viewModel.validLogin.observe(getViewLifecycleOwner(), loginObserver);
    }


    public void createSession() {
        DBAuth = FirebaseAuth.getInstance();
        userLoggedIn = DBAuth.getCurrentUser();
        session = new SessionManagement(getActivity().getApplicationContext());
        session.saveSession(userLoggedIn);
    }
}