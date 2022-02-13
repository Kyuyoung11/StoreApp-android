package com.example.storeapp.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.storeapp.databinding.ActivityFragmentBinding;
import com.example.storeapp.databinding.ActivityLoginBinding;

public class FragmentActivity extends AppCompatActivity {
    private ActivityFragmentBinding binding;
    String words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFragmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSearch.setEnabled(false);
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                words = editable.toString().trim();

                if (words.length() == 0) {
                    binding.btnSearch.setEnabled(false);
                    binding.btnSearch.setBackgroundColor(Color.GRAY);
                } else {
                    binding.btnSearch.setEnabled(true);
                    binding.btnSearch.setBackgroundColor(Color.WHITE);
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
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(binding.frameLayout.getId(), mainFragment)
                    .commit();
        });
    }
}