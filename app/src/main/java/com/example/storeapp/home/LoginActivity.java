package com.example.storeapp.home;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.storeapp.databinding.ActivityLoginBinding;
import com.example.storeapp.task.Task;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSign.setOnClickListener(v -> {
            if (binding.etId.getText().toString().length() > 0 &&
            binding.etPw.getText().toString().length() > 0) {
                login();


            }
        });
        binding.btnSign.setOnClickListener(v -> {
           Intent intent = null;
           intent = new Intent(this, SignupActivity.class);
           startActivity(intent);
        });
    }

    void login() {
        Log.w("login", "로그인 하는 중");
        try {
            String id = binding.etId.getText().toString();
            String passwd = binding.etPw.getText().toString();

            String result = new Task().execute(id, passwd).get();
            Log.w("login", result);

            Toast.makeText(this, "login done", Toast.LENGTH_SHORT).show();

            finish();
        }catch (Exception e) {

        }



    }




}