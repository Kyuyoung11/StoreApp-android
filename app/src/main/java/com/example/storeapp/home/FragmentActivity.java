package com.example.storeapp.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.storeapp.R;
import com.example.storeapp.databinding.ActivityFragmentBinding;
import com.example.storeapp.dto.GlobalVar;

//코드 참고
// 전역 변수 : http://slog2.egloos.com/v/3896766
public class FragmentActivity extends AppCompatActivity {
    private static final String TAG = "fragmentactivity";
    private ActivityFragmentBinding binding;
    String words;
    int color_btnb, color_btnf;
    GlobalVar gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFragmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        gv = (GlobalVar)getApplicationContext();

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
            binding.linBar.setVisibility(View.VISIBLE);
            InputMethodManager mInputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            mInputMethodManager.hideSoftInputFromWindow(binding.etSearch.getWindowToken(), 0);
            SearchFragment searchFragment = new SearchFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(binding.frameLayout.getId(), searchFragment.newInstance(words))
                    .commit();
        });


        binding.btnHome.setOnClickListener(v-> {
            binding.linBar.setVisibility(View.VISIBLE);
            binding.etSearch.setText("");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(binding.frameLayout.getId(), mainFragment)
                    .commit();
        });

        binding.btnUser.setOnClickListener(v -> {
            if (!gv.isLogin()) {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        });

        binding.btnCart.setOnClickListener(v -> {
            if (gv.isLogin()) {
                binding.linBar.setVisibility(View.GONE);
                CartFragment cartFragment = new CartFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(binding.frameLayout.getId(), cartFragment.newInstance(Long.toString(gv.getId())))
                        .addToBackStack(null)
                        .commit();
            }
            else {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }

        });
    }
}