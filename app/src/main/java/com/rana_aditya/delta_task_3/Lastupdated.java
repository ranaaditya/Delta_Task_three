package com.rana_aditya.delta_task_3;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Lastupdated {
    @SerializedName("date")
    Date date;

    public Lastupdated(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
