package com.rana_aditya.delta_task_3;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Street implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    public Street(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
