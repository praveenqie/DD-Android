package com.example.developerdiary.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private String authToken;

    public AuthInterceptor() {
        // Empty constructor
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        // Add the Authorization header with the Bearer token if authToken is not null
        Request.Builder requestBuilder = originalRequest.newBuilder();
        if (authToken != null) {
            requestBuilder.header("Authorization", "Bearer " + authToken);
        }

        Request newRequest = requestBuilder.build();
        Response response = chain.proceed(newRequest);

        // Check if the response contains an "Authorization failed" error
        if (response.code() == 401 && authToken != null) {
            // Token is expired; handle token refresh here

            // Get the new token after refreshing
            String newToken = "YOUR_REFRESHED_TOKEN_HERE";

            // Update the AuthInterceptor with the new token
            setAuthToken(newToken);

            // Retry the request with the new token
            Request retryRequest = newRequest.newBuilder()
                    .header("Authorization", "Bearer " + newToken)
                    .build();

            return chain.proceed(retryRequest);
        }

        return response;
    }
}
