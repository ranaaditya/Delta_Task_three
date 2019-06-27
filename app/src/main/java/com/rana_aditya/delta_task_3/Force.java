package com.rana_aditya.delta_task_3;

import com.google.gson.annotations.SerializedName;

public class Force {

    @SerializedName("id")
    private  String id;
    @SerializedName("name")
    private String name;

    public Force(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
