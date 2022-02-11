package com.example.storeapp.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductListResponse {
    @SerializedName("products")
    List<ProductResponse> productsList = null;

    public List<ProductResponse> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<ProductResponse> productsList) {
        this.productsList = productsList;
    }
}
