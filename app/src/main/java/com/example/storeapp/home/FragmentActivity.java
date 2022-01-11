package com.example.storeapp.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.storeapp.databinding.ActivityFragmentBinding;
import com.example.storeapp.databinding.ActivityLoginBinding;

public class FragmentActivity extends AppCompatActivity {
    private ActivityFragmentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFragmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        MainFragment mainFragment = new MainFragment();
        ft.replace(binding.frameLayout.getId(), mainFragment);
        ft.commit();

        binding.btnSearch.setOnClickListener(v-> {
            SearchFragment searchFragment = new SearchFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(binding.frameLayout.getId(), searchFragment)
                    .commit();
        });
    }
}