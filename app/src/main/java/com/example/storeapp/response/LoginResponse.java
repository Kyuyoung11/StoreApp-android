package com.example.storeapp.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("name")
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //    @SerializedName("result")
//    public String resultCode;
//
//    public void setResultCode(String resultCode) {
//        this.resultCode = resultCode;
//    }
//
//    public String getResultCode() {
//        return resultCode;
//    }
}