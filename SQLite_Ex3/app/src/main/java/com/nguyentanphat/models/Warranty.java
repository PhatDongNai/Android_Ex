package com.nguyentanphat.models;

public class Warranty {
    int warrantyID;
    String warrantyName;
    String getWarrantyDescription;
    byte[] warrantyPhoto;
    //Constructor

    public Warranty(int warrantyID, String warrantyName, String getWarrantyDescription, byte[] warrantyPhoto) {
        this.warrantyID = warrantyID;
        this.warrantyName = warrantyName;
        this.getWarrantyDescription = getWarrantyDescription;
        this.warrantyPhoto = warrantyPhoto;
    }

    public int getWarrantyID() {
        return warrantyID;
    }

    public void setWarrantyID(int warrantyID) {
        this.warrantyID = warrantyID;
    }

    public String getWarrantyName() {
        return warrantyName;
    }

    public void setWarrantyName(String warrantyName) {
        this.warrantyName = warrantyName;
    }

    public String getGetWarrantyDescription() {
        return getWarrantyDescription;
    }

    public void setGetWarrantyDescription(String getWarrantyDescription) {
        this.getWarrantyDescription = getWarrantyDescription;
    }

    public byte[] getWarrantyPhoto() {
        return warrantyPhoto;
    }

    public void setWarrantyPhoto(byte[] warrantyPhoto) {
        this.warrantyPhoto = warrantyPhoto;
    }
}
