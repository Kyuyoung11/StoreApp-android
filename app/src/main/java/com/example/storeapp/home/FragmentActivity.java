package com.example.storeapp.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.example.storeapp.R;
import com.example.storeapp.databinding.ActivityFragmentBinding;
import com.example.storeapp.databinding.ActivityLoginBinding;

public class FragmentActivity extends AppCompatActivity {
    private static final String TAG = "fragmentactivity";
    private ActivityFragmentBinding binding;
    String words;
    int color_btnb, color_btnf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFragmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        color_btnb = ContextCompat.getColor(this, R.color.btn_basic);
        color_btnf = ContextCompat.getColor(this, R.color.btn_false);

        binding.btnSearch.setEnabled(false);
        binding.btnSearch.setBackgroundColor(color_btnf);
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.btnSearch.setBackgroundColor(color_btnb);


            }

            @Override
            public void afterTextChanged(Editable editable) {
                words = editable.toString().trim();
                Log.d(TAG, words.length() + "");

                if (words.length() > 0) {
                    binding.btnSearch.setEnabled(true);
                    binding.btnSearch.setBackgroundColor(color_btnb);

                } else {
                    binding.btnSearch.setEnabled(false);
                    binding.btnSearch.setBackgroundColor(color_btnf);

                }

            }
        });

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        MainFragment mainFragment = new MainFragment();
        ft.replace(binding.frameLayout.getId(), mainFragment);
        ft.commit();



        binding.btnSearch.setOnClickListener(v-> {
            SearchFragment searchFragment = new SearchFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(binding.frameLayout.getId(), searchFragment.newInstance(words))
                    .commit();
        });


        binding.btnHome.setOnClickListener(v-> {
            binding.etSearch.setText("");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(binding.frameLayout.getId(), mainFragment)
                    .commit();
        });

        binding.btnUser.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }
}