package com.example.developerdiary.api;

public class ApiUtils {

    public static ApiService getApiService() {
        return RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }
}
