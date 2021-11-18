package com.example.storeapp.response;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("id")
    public Long id;

    @SerializedName("name")
    public String name;

    @SerializedName("pw")
    public String pw;

    @SerializedName("link")
    public String link;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPw() {
        return pw;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                "\nname=" + name
                + "}";
    }
}
