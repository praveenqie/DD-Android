package com.example.developerdiary.api;

import android.content.Context;

import com.example.developerdiary.database.UserRepository;
import com.example.developerdiary.ui.service.HomeService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {
    private static ApiService apiService;


    private static Context context;

    public static void initialize(Context context) {
        ApiUtils.context = context.getApplicationContext();
    }
    private static final String BASE_URL = "http://192.168.0.108:8080/";

    public ApiUtils(Context context) {
        context = context;
    }

    public static ApiService getApiService(Boolean isLoginApi) {
            Retrofit retrofit;
            if(isLoginApi) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }else{
                UserRepository userRepository = new UserRepository(context);
                userRepository.open();
                // Create the AuthInterceptor and set the initial token (if available)
                AuthInterceptor authInterceptor = new AuthInterceptor();

                authInterceptor.setAuthToken(userRepository.getUser().getAccess_token());

                OkHttpClient httpClient = new OkHttpClient.Builder()
                        .addInterceptor(authInterceptor)
                        .build();
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient)
                        .build();
            }

            apiService = retrofit.create(ApiService.class);
        return apiService;
    }
}
