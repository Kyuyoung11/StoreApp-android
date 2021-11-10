package com.example.storeapp.task;

import com.example.storeapp.request.LoginRequest;
import com.example.storeapp.response.LoginResponse;
import com.example.storeapp.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface initMyApi {
    @POST("api/users")
    Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);

    @GET("api/users/1")
    Call<UserResponse> requestGetUserDetail();


}
