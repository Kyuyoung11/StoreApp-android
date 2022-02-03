package com.example.storeapp.response;

import com.google.gson.annotations.SerializedName;

public class ProductResponse {

    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;
    @SerializedName("price")
    private int price;
    @SerializedName("writer")
    private String writer;
    @SerializedName("company")
    private String company;
    @SerializedName("detail")
    private String detail;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
