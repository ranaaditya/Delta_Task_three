package com.rana_aditya.delta_task_3;

import com.google.gson.annotations.SerializedName;

public class Crime_Category {
    @SerializedName("url")
    private String url;
    @SerializedName("name")
    private String name;

    public Crime_Category(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
