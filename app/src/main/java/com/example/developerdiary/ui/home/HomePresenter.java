package com.example.developerdiary.ui.home;

import android.content.Context;

import com.example.developerdiary.ui.dto.Tutorial;
import com.example.developerdiary.ui.service.HomeService;

import java.util.List;

public class HomePresenter implements HomeService.presenter ,HomeService.OnFetchTutorialFinishedListener {

    private HomeService.view view;

    private HomeService.model model;

    public HomePresenter(HomeService.view view){
        this.view = view;
        model = new HomeModel();
    }
    @Override
    public void FetchAllTutorials() {
//        view.showProgress();
       model.FetchAllTutorials(this);
    }

    @Override
    public void FetchAllTutorials(List<Tutorial> tutorialList) {
        view.SuccessFetchAllTutorials(tutorialList);
    }

    @Override
    public void onApiError(String errorMessage) {
        view.hideProgress(); // Hide the loading indicator if needed
        view.apiError(errorMessage);
    }

}
