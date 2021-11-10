package com.example.storeapp.home;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storeapp.MainActivity;
import com.example.storeapp.databinding.ActivityLoginBinding;
import com.example.storeapp.request.LoginRequest;
import com.example.storeapp.response.LoginResponse;
import com.example.storeapp.response.UserResponse;
import com.example.storeapp.task.RetrofitClient;
import com.example.storeapp.task.Task;
import com.example.storeapp.task.initMyApi;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private static final String TAG = "LoginActivity";

    private RetrofitClient retrofitClient;
    private initMyApi initMyApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //retrofit 생성
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        binding.btnLogin.setOnClickListener(v -> {
            if (binding.etId.getText().toString().trim().length() > 0 &&
            binding.etPw.getText().toString().trim().length() > 0) {
                login();

            }
        });
        binding.btnSign.setOnClickListener(v -> {
           Intent intent = null;
           intent = new Intent(this, SignupActivity.class);
           startActivity(intent);
        });

        binding.btnCheck.setOnClickListener(v -> {
            check();
        });
    }

    public void login() {
        Log.w("login", "로그인 하는 중");
        String name = binding.etId.getText().toString().trim();
        String passwd = binding.etPw.getText().toString().trim();

        LoginRequest loginRequest = new LoginRequest(name, passwd);

//        //retrofit 생성
//        retrofitClient = RetrofitClient.getInstance();
//        initMyApi = RetrofitClient.getRetrofitInterface();

        //LoginRequest 실행해서 보냄
        initMyApi.getLoginResponse(loginRequest).enqueue(
                new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        Log.w("login", "data success");
                        if (response.isSuccessful() && response.body() != null) {
                            LoginResponse result = response.body();

                            String resultCode = result.getName();

                            String success = "asdf"; //로그인 성공
                            String errorId = "300"; //아이디 일치x
                            String errorPw = "400"; //비밀번호 일치x

                            if (resultCode.equals(success)) {
                                String id = binding.etId.getText().toString().trim();
                                String passwd = binding.etPw.getText().toString().trim();
                                
                                Toast.makeText(LoginActivity.this, id + " 로그인", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("userId", id);
                                startActivity(intent);
                                LoginActivity.this.finish();
                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "실패", Toast.LENGTH_SHORT).show();
                    }
                }
        );





    }

    public void check() {
        Call<UserResponse> call = initMyApi.requestGetUserDetail();
        call.enqueue(new Callback<UserResponse>(){
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Toast.makeText(LoginActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "실패", Toast.LENGTH_SHORT).show();

            }
        });
    }




}