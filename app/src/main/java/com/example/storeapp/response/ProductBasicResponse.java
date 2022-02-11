package com.example.storeapp.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductBasicResponse {
    @SerializedName("_embedded")
    ProductListResponse productListResponse;

    public void setProductListResponse(ProductListResponse productListResponse) {
        this.productListResponse = productListResponse;
    }

    public ProductListResponse getProductListResponse() {
        return productListResponse;
    }
}
