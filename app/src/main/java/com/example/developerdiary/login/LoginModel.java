package com.example.developerdiary.login;

import android.content.Context;

import com.example.developerdiary.api.ApiService;
import com.example.developerdiary.api.ApiUtils;
import com.example.developerdiary.api.AuthInterceptor;
import com.example.developerdiary.database.UserRepository;
import com.example.developerdiary.login.dto.LoginRequest;
import com.example.developerdiary.login.dto.LoginResponse;
import com.example.developerdiary.login.service.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginModel implements LoginService.Model {

    AuthInterceptor authInterceptor = new AuthInterceptor();

    @Override
    public void performLogin(LoginRequest loginRequest, final LoginService.OnLoginFinishedListener listener) {

        ApiService apiService = ApiUtils.getApiService(true);
        Call<LoginResponse> call = apiService.login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null) {
                        listener.onLoginSuccess(loginResponse);
                    } else {
                        listener.onLoginError("Invalid username or password");
                    }
                } else {
                    listener.onLoginError("API call failed");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                listener.onLoginError("Network Error");
            }
        });
    }
}
