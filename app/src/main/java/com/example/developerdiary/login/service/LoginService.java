package com.example.developerdiary.login.service;

import android.content.Context;

import com.example.developerdiary.login.dto.LoginRequest;
import com.example.developerdiary.login.dto.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface LoginService {
    interface View {
        void showLoginSuccess(LoginResponse loginResponse);
        void showLoginError(String errorMessage);
        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        void login(LoginRequest loginRequest);
        void onDestroy();
    }

    interface Model {
        void performLogin(LoginRequest loginRequest, OnLoginFinishedListener listener);
    }

    interface OnLoginFinishedListener {
        void onLoginSuccess(LoginResponse loginResponse);
        void onLoginError(String errorMessage);
    }
//    public interface ApiService {
//
//        @POST("/api/v1/auth/login")
//        Call<LoginResponse> login(@Body LoginRequest loginRequest);
//    }
}
