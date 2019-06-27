package com.rana_aditya.delta_task_3;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Location implements Serializable {
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("street")
    private Street street;
    @SerializedName("longitude")
    private double longitude;

    public Location(double latitude, Street street, double longitude) {
        this.latitude = latitude;
        this.street = street;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public Street getStreet() {
        return street;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
