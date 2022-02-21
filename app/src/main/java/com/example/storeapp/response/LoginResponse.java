package com.example.storeapp.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("pw")
    private String pw;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
