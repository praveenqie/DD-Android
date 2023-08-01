package com.example.developerdiary.login;

import com.example.developerdiary.api.ApiService;
import com.example.developerdiary.api.ApiUtils;
import com.example.developerdiary.login.dto.LoginRequest;
import com.example.developerdiary.login.dto.LoginResponse;
import com.example.developerdiary.login.service.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginModel implements LoginService.Model {

    @Override
    public void performLogin(LoginRequest loginRequest, final LoginService.OnLoginFinishedListener listener) {
        //ApiService apiService = ApiService.create(ApiService.class);
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.0.108:8080/api/v1/auth/") // Replace with your API base URL
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        apiService = retrofit.create(LoginService.ApiService.class);
        ApiService apiService = ApiUtils.getApiService();
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
