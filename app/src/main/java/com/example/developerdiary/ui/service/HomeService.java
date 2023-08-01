package com.example.developerdiary.ui.service;

import com.example.developerdiary.login.dto.LoginRequest;
import com.example.developerdiary.login.dto.LoginResponse;
import com.example.developerdiary.login.service.LoginService;
import com.example.developerdiary.ui.dto.Tutorial;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HomeService {

    interface model{
        void FetchAllTutorials(OnFetchTutorialFinishedListener listener);
    }

    interface view{
        void SuccessFetchAllTutorials(List<Tutorial> tutorialList);


         void showProgress();
        void hideProgress();

        void apiError(String errorMessage);
    }

    interface presenter{

       void FetchAllTutorials();
    }

    interface ApiService {
        @POST("/api/v1/tutorial")
        Call<List<Tutorial>> findAllTutorial();
    }
    interface OnFetchTutorialFinishedListener {
       void FetchAllTutorials(List<Tutorial> tutorialList);
        void onApiError(String errorMessage);
    }
}
