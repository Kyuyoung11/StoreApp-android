package com.example.storeapp.task;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//https://code-hyoon.tistory.com/9 코드 참고
public class RetrofitClient {
    private static RetrofitClient instance = null;
    private static MyAPI initMyApi;
    private static String baseUrl = "http://10.0.2.2:8080/";

    private RetrofitClient() {
        //로그를 보기 위한 Interceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client) //로그 기능 추가
                .build();
        initMyApi = retrofit.create(MyAPI.class);
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public static MyAPI getRetrofitInterface() {
        return initMyApi;
    }

}
