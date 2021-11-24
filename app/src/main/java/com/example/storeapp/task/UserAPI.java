package com.example.storeapp.task;

import com.example.storeapp.request.LoginRequest;
import com.example.storeapp.response.LoginResponse;
import com.example.storeapp.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserAPI {
    @POST("api/users/signup")
    Call<LoginResponse> getSignupResponse(@Body LoginRequest loginRequest);

    @POST("api/users/login")
    Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);

    @GET("api/users/1")
    Call<UserResponse> requestGetUserDetail();

    @GET("api/users/{name}/exists")
    Call<Boolean> checkNameExists(@Path("name") String name);





}
