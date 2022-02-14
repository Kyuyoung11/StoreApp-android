package com.example.storeapp.request;

import com.google.gson.annotations.SerializedName;

public class StringRequest {
    @SerializedName("str")
    public String str;

    public StringRequest(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
