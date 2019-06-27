package com.rana_aditya.delta_task_3;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Outcome_Status implements Serializable {

    @SerializedName("category")
    private String categoty;
    @SerializedName("date")
    private String date;

    public Outcome_Status(String categoty, String date) {
        this.categoty = categoty;
        this.date = date;
    }

    public void setCategoty(String categoty) {
        this.categoty = categoty;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategoty() {
        return categoty;
    }

    public String getDate() {
        return date;
    }
}

