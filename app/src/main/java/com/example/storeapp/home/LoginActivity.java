package com.example.storeapp.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storeapp.databinding.ActivityLoginBinding;
import com.example.storeapp.dto.GlobalVar;
import com.example.storeapp.request.LoginRequest;
import com.example.storeapp.response.LoginResponse;
import com.example.storeapp.task.RetrofitClient;
import com.example.storeapp.task.MyAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private static final String TAG = "LoginActivity";

    private RetrofitClient retrofitClient;
    private MyAPI myAPI;
    GlobalVar user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = (GlobalVar)getApplicationContext();


//        //retrofit 생성
//        retrofitClient = RetrofitClient.getInstance();
//        initMyApi = RetrofitClient.getRetrofitInterface();

        binding.btnLogin.setOnClickListener(v -> {
            if (binding.etId.getText().toString().trim().length() > 0 &&
                    binding.etPw.getText().toString().trim().length() > 0) {
                login();

            }
        });
        binding.btnSign.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        });

    }

    public void login() {
        Log.w("login", "로그인 하는 중");
        String name = binding.etId.getText().toString().trim();
        String passwd = binding.etPw.getText().toString().trim();

        LoginRequest loginRequest = new LoginRequest(name, passwd);

        //retrofit 생성
        retrofitClient = RetrofitClient.getInstance();
        myAPI = RetrofitClient.getRetrofitInterface();

        //LoginRequest 실행해서 보냄
        myAPI.getLoginResponse(loginRequest).enqueue(
                new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        String resultCode = Integer.toString(response.code());
                        Log.w("login", resultCode);

                        if (response.isSuccessful() && resultCode.equals("200")) {

                                LoginResponse result = response.body();

                                user.setLogin(true);
                                user.setId(result.getId());

                                String id = binding.etId.getText().toString().trim();
                                String passwd = binding.etPw.getText().toString().trim();

                                Intent intent = new Intent(LoginActivity.this, FragmentActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.putExtra("userId", id);

                                startActivity(intent);
                                LoginActivity.this.finish();




                        }
                        else if (resultCode.equals("400")) {
                            Toast.makeText(LoginActivity.this, "비밀번호가 옳지 않습니다.", Toast.LENGTH_LONG).show();
                        }

                        else if (resultCode.equals("404")) {
                            Toast.makeText(LoginActivity.this, "존재하지 않는 사용자입니다.", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "실패", Toast.LENGTH_SHORT).show();
                    }
                }
        );


    }



}