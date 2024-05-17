package com.example.models;

import androidx.annotation.NonNull;

public class Product {
    String productName;
    Double productPrice;

    //constructor
    public Product(String productName, Double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    //Getter and setter
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    @NonNull
    @Override
    public String toString() {
        return this.productName + "-" + this.productPrice;
    }
}
