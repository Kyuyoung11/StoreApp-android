package com.example.storeapp.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.storeapp.MainActivity;
import com.example.storeapp.databinding.ActivitySignupBinding;
import com.example.storeapp.request.LoginRequest;
import com.example.storeapp.response.LoginResponse;
import com.example.storeapp.response.UserResponse;
import com.example.storeapp.task.RetrofitClient;
import com.example.storeapp.task.UserAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;
    private static final String TAG = "SignupActivity";

    private RetrofitClient retrofitClient;
    private UserAPI userAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnSignup.setOnClickListener(v -> {
            signup();

        });

        binding.btnId.setOnClickListener(v -> {
            checkId();
        });
    }


    public void signup() {
        Log.w(TAG, "가입 하는 중");
        String name = binding.etSignid.getText().toString().trim();
        String passwd = binding.etSignpw.getText().toString().trim();

        LoginRequest loginRequest = new LoginRequest(name, passwd);

        //retrofit 생성
        retrofitClient = RetrofitClient.getInstance();
        userAPI = RetrofitClient.getRetrofitInterface();

        //LoginRequest 실행해서 보냄
        userAPI.getSignupResponse(loginRequest).enqueue(
                new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        Log.w("login", "data success");
                        if (response.isSuccessful() && response.body() != null) {
                            LoginResponse result = response.body();

                            String id = binding.etSignid.getText().toString().trim();
                            String passwd = binding.etSignpw.getText().toString().trim();

                            Toast.makeText(SignupActivity.this, "가입 완료", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            intent.putExtra("userId", id);
                            startActivity(intent);
                            SignupActivity.this.finish();


                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(SignupActivity.this, "연결 실패", Toast.LENGTH_SHORT).show();
                    }
                }
        );


    }

    public void checkId() {
        Log.w(TAG, "가입 하는 중");
        String name = binding.etSignid.getText().toString().trim();

        //retrofit 생성
        retrofitClient = RetrofitClient.getInstance();
        userAPI = RetrofitClient.getRetrofitInterface();

        Call<Boolean> call = userAPI.checkNameExists(name);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.body()) {
                    Toast.makeText(SignupActivity.this, "이미 있음", Toast.LENGTH_SHORT).show();
                    binding.txtResultcheck.setTextColor(Color.RED);
                    binding.txtResultcheck.setText("이미 존재하는 아이디입니다.");
                    binding.btnSignup.setEnabled(false);


                } else {
                    Toast.makeText(SignupActivity.this, "사용 가능", Toast.LENGTH_SHORT).show();
                    binding.txtResultcheck.setTextColor(Color.GREEN);
                    binding.txtResultcheck.setText("사용 가능한 아이디입니다.");
                    binding.btnSignup.setEnabled(true);

                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "실패", Toast.LENGTH_SHORT).show();

            }
        });

    }
}