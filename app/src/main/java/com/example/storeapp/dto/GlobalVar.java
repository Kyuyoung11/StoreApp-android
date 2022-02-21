package com.example.storeapp.dto;

import android.app.Application;

//코드 참고
// 전역 변수 : http://slog2.egloos.com/v/3896766
public class GlobalVar extends Application {
    private Long id;
    private boolean isLogin;
    private boolean isVisible;



    @Override
    public void onCreate() {
        id = null;
        isLogin = false;
        isVisible = true;
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public Long getId() {
        return id;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
