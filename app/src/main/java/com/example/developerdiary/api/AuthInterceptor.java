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

            // Synchronize token refresh to avoid multiple concurrent refresh attempts
            synchronized (this) {
                // Assuming you have a method to refresh the token and obtain the new token
                String newToken = refreshToken();

                if (newToken != null) {
                    // Update the AuthInterceptor with the new token
                    setAuthToken(newToken);

                    // Retry the request with the new token
                    Request retryRequest = newRequest.newBuilder()
                            .header("Authorization", "Bearer " + newToken)
                            .build();

                    return chain.proceed(retryRequest);
                } else {
                    // If token refresh fails, you can handle the logout or other error flow
                    // For example:
                    // logoutUser();
                    // Or show an error message to the user
                }
            }
        }

        return response;
    }

    // Method to refresh the token (replace this with your token refresh logic)
    private String refreshToken() {
        // TODO: Implement your token refresh logic here and return the refreshed token
        // If refresh is successful, return the new token; otherwise, return null
        return "YOUR_REFRESHED_TOKEN_HERE";
    }
}
