package com.example.developerdiary.login;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.developerdiary.R;
import com.example.developerdiary.database.UserRepository;
import com.example.developerdiary.login.dto.LoginRequest;
import com.example.developerdiary.login.dto.LoginResponse;
import com.example.developerdiary.login.service.LoginService;

public class LoginFragment extends Fragment implements  LoginService.View {
    private EditText emailText;
    private EditText passwordText;
    private Button loginButton;
    private ProgressBar progressBar;
    private LoginService.Presenter presenter;
    private FragmentCommunicationListener communicationListener;

    private LoginRequest loginRequest;

    public interface FragmentCommunicationListener {
        void onLoginSuccess();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login_tab, container, false);
        emailText = (EditText) rootView.findViewById(R.id.login_email);
        passwordText = (EditText) rootView.findViewById(R.id.login_password);
        loginButton = (Button) rootView.findViewById(R.id.login_button);
        progressBar = rootView.findViewById(R.id.progressBar);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                loginRequest = new LoginRequest("praveen@gmail.com","password@123");
                performLogin(loginRequest);
            }
        });

        return rootView;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            communicationListener = (FragmentCommunicationListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement FragmentCommunicationListener");
        }
    }

    private void performLogin(LoginRequest loginRequest) {
        presenter = new LoginPresenter(this);
        presenter.login(loginRequest);
    }

    @Override
    public void showLoginSuccess(LoginResponse loginResponse) {
     Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
        Log.d("Login response",loginResponse.toString());
        UserRepository userRepository = new UserRepository(getContext());
        userRepository.open();
        userRepository.addUser(loginResponse.getUsersData().getEmail(),
                loginResponse.getAccess_token(),loginResponse.getRefresh_token(),loginResponse.getUsersData().getFirstname(),
                loginResponse.getUsersData().getLastname());
        communicationListener.onLoginSuccess();
    }

    @Override
    public void showLoginError(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
    }

}
