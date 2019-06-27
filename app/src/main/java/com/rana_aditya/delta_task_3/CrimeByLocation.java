package com.rana_aditya.delta_task_3;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class CrimeByLocation implements Serializable {
    @SerializedName("category")
    private String category;
    @SerializedName("location_type")
    private String location_type;
    @SerializedName("location")
    private Location location;
    @SerializedName("context")
    private String context;
    @SerializedName("outcome_status")
    private Outcome_Status outcome_status;
    @SerializedName("persistence_id")
    private String persistence_id;
    @SerializedName("id")
    private int id;
    @SerializedName("location_subtype")
    private String location_subtype;
    @SerializedName("month")
     private String month;

    public CrimeByLocation(String category, String location_type, Location location, String context, Outcome_Status outcome_status, String persistence_id, int id, String location_subtype, String month) {
        this.category = category;
        this.location_type = location_type;
        this.location = location;
        this.context = context;
        this.outcome_status = outcome_status;
        this.persistence_id = persistence_id;
        this.id = id;
        this.location_subtype = location_subtype;
        this.month = month;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

    public void setOutcome_status(Outcome_Status outcome_status) {
        this.outcome_status = outcome_status;
    }

    public void setPersistence_id(String persistence_id) {
        this.persistence_id = persistence_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLocation_subtype(String location_subtype) {
        this.location_subtype = location_subtype;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation_type() {
        return location_type;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Outcome_Status getOutcome_status() {
        return outcome_status;
    }

    public String getPersistence_id() {
        return persistence_id;
    }

    public int getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public String getLocation_subtype() {
        return location_subtype;
    }

    public String getMonth() {
        return month;
    }
}
