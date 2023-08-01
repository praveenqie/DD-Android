package com.example.developerdiary.login;

import com.example.developerdiary.login.dto.LoginRequest;
import com.example.developerdiary.login.dto.LoginResponse;
import com.example.developerdiary.login.service.LoginService;

public class LoginPresenter implements LoginService.Presenter, LoginService.OnLoginFinishedListener {
    private LoginService.View view;
    private LoginService.Model model;

    public LoginPresenter(LoginService.View view) {
        this.view = view;
        model = new LoginModel();
    }

    @Override
    public void login(LoginRequest loginRequest) {
        view.showProgress(); // Show a loading indicator if needed
        model.performLogin(loginRequest, this);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onLoginSuccess(LoginResponse loginResponse) {
        view.hideProgress(); // Hide the loading indicator if needed
        view.showLoginSuccess(loginResponse);
    }

    @Override
    public void onLoginError(String errorMessage) {
        view.hideProgress(); // Hide the loading indicator if needed
        view.showLoginError(errorMessage);
    }
}
