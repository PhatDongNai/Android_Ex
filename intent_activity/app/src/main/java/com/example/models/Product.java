package com.example.models;

import java.io.Serializable;

public class Product implements Serializable {
    int productCode;
    String productName;
    double productPrice;

    //constructor
    public Product(int productCode, String productName, double productPrice) {
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    //getter setter

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }
}
