package com.nguyentanphat.test.models;

public class Product {
    String placeName;
    String dishName;
    int photo;
    Float ratingValue;
    String ratingCount;
    String address;

    public Product(String placeName, String dishName, int photo, Float ratingValue, String ratingCount, String address) {
        this.placeName = placeName;
        this.dishName = dishName;
        this.photo = photo;
        this.ratingValue = ratingValue;
        this.ratingCount = String.valueOf(ratingCount);
        this.address = address;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public Float getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(Float ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(String ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
