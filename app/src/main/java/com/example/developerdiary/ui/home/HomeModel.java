package com.example.developerdiary.ui.home;

import android.content.Context;

import com.example.developerdiary.api.ApiService;
import com.example.developerdiary.api.ApiUtils;
import com.example.developerdiary.api.AuthInterceptor;
import com.example.developerdiary.login.dto.LoginResponse;
import com.example.developerdiary.login.service.LoginService;
import com.example.developerdiary.ui.dto.Tutorial;
import com.example.developerdiary.ui.service.HomeService;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeModel implements HomeService.model {

    @Override
    public void FetchAllTutorials(HomeService.OnFetchTutorialFinishedListener listener) {


        ApiService apiService = ApiUtils.getApiService(false);
        Call<List<Tutorial>> call = apiService.findAllTutorial();
        call.enqueue(new Callback<List<Tutorial>>() {
            @Override
            public void onResponse(Call<List<Tutorial>> call, Response<List<Tutorial>> response) {
                if (response.isSuccessful()) {
                    List<Tutorial> tutorialList = response.body();
                    if (tutorialList.size() != 0 && Objects.nonNull(tutorialList)) {
                        listener.FetchAllTutorials(tutorialList);
                    } else {
                        listener.onApiError("Invalid username or password");
                    }
                } else {
                    listener.onApiError("API call failed");
                }
            }

            @Override
            public void onFailure(Call<List<Tutorial>> call, Throwable t) {

            }
        });
    }
}
