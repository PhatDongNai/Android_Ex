package com.nguyentanphat.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Product implements Serializable {
    int productCode;
    String productName;
    double getProductPrice;

    public Product(int productCode, String productName, double getProductPrice) {
        this.productCode = productCode;
        this.productName = productName;
        this.getProductPrice = getProductPrice;
    }

    //Getter and setter
    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getGetProductPrice() {
        return getProductPrice;
    }

    public void setGetProductPrice(int getProductPrice) {
        this.getProductPrice = getProductPrice;
    }

    @NonNull
    @Override
    public String toString() {
        return productCode + ": " +  productName + "-" + String.format("%.0fđ",getProductPrice);
    }
}
