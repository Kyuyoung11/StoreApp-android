package com.example.storeapp.task;

import com.example.storeapp.dto.BookDTO;
import com.example.storeapp.request.LoginRequest;
import com.example.storeapp.response.LoginResponse;
import com.example.storeapp.response.ProductBasicResponse;
import com.example.storeapp.response.ProductListResponse;
import com.example.storeapp.response.ProductResponse;
import com.example.storeapp.response.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyAPI {
    @POST("api/users/signup")
    Call<LoginResponse> getSignupResponse(@Body LoginRequest loginRequest);

    @POST("api/users/login")
    Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);

    @GET("api/users/6")
    Call<UserResponse> requestGetUserDetail();

    @GET("api/users/{name}/exists")
    Call<Boolean> checkNameExists(@Path("name") String name);


    @GET("api/products")
    Call<ProductBasicResponse> getAllProducts();





}
