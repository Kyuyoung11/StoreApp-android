package com.example.storeapp.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.storeapp.databinding.ActivitySignupBinding;
import com.example.storeapp.dto.UserDTO;
import com.example.storeapp.service.RetrofitService;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class SignupActivity extends AppCompatActivity {
    private ActivitySignupBinding binding;
    private static final String TAG = "SignupActivity";
    private RetrofitService retrofitService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnSignup.setOnClickListener(v -> {
            UserDTO dto = new UserDTO();
            dto.setName(binding.etId.getText().toString());
            dto.setPw(binding.etPw.getText().toString());

            Gson gson = new Gson();
            String objJson = gson.toJson(dto);

            Call<ResponseBody> signup = retrofitService.goPost(objJson);

        });
    }
}