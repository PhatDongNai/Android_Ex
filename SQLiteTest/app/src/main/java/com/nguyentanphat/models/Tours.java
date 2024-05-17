package com.nguyentanphat.models;

public class Tours {
    int tourCode;
    String tourName;
    double tourPrice;
    String tourDescription;
    String photo;

    //constructor

    public Tours(int tourCode, String tourName, double tourPrice, String tourDescription, String photo) {
        this.tourCode = tourCode;
        this.tourName = tourName;
        this.tourPrice = tourPrice;
        this.tourDescription = tourDescription;
        this.photo = photo;
    }

    public int getTourCode() {
        return tourCode;
    }

    public void setTourCode(int tourCode) {
        this.tourCode = tourCode;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    public double getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(double tourPrice) {
        this.tourPrice = tourPrice;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
