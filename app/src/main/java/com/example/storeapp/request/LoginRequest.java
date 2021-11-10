package com.example.storeapp.request;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("name")
    public String name;

    @SerializedName("pw")
    public String pw;

    public LoginRequest(String name, String pw) {
        this.name = name;
        this.pw = pw;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public String getPw() {
        return pw;
    }
}
