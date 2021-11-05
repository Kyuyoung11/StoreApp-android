package com.example.storeapp.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;


public class UserDTO {
    @SerializedName("name")
    private String name;

    @SerializedName("pw")
    private String pw;

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

    public UserDTO() {}


    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", pw=" + pw + '\'' +
                "}";
    }

}
