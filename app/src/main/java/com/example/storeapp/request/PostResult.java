package com.example.storeapp.request;

import com.google.gson.annotations.SerializedName;

public class PostResult {
    @SerializedName("name")
    private String name;

    @SerializedName("pw")
    private String pw;

    @Override
    public String toString() {
        return "PostResult{" +
                "name='" + name + '\'' +
                ", pw=" + pw + '\'' +
                "}";
    }
}
