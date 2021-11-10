package com.example.storeapp.service;

import com.example.storeapp.request.PostResult;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {
    @GET("api/users/{id}")
    Call<PostResult> getPosts(@Path("id") Long id);

    @FormUrlEncoded
    @POST("api/users")
    Call<ResponseBody> goPost(@Field("objJson") String objJson);
}
