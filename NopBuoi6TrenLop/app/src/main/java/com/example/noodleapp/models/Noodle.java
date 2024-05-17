package com.example.noodleapp.models;

import java.io.Serializable;

public class Noodle implements Serializable {
    int thumb;
    String infor;

    String subTitle;

    String rate;

    String distance;

    //constructor

    public Noodle(int thumb, String infor, String subTitle, String rate, String distance) {
        this.thumb = thumb;
        this.infor = infor;
        this.subTitle = subTitle;
        this.rate = rate;
        this.distance = distance;
    }

    //getter and setter

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Noodle{" +
                "thumb=" + thumb +
                ", infor='" + infor + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", rate='" + rate + '\'' +
                ", distance='" + distance + '\'' +
                '}';
    }
}
