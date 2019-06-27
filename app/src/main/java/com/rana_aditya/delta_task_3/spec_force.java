package com.rana_aditya.delta_task_3;

import com.google.gson.annotations.SerializedName;

public class spec_force  {
    @SerializedName("engagement_methods")
    private Engagement_methods engagement_methods[]=new Engagement_methods[4];
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private  String name;
    @SerializedName("description")
    private String description;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("url")
    private String url;
    @SerializedName("telephone")
    private int telephone;



    public void setEngagement_methods(Engagement_methods[] engagement_methods) {
        this.engagement_methods = engagement_methods;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public spec_force(Engagement_methods[] engagement_methods, String id, String name, String description, String url, int telephone) {
        this.engagement_methods = engagement_methods;
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.telephone = telephone;
    }


    public Engagement_methods[] getEngagement_methods() {
        return engagement_methods;
    }

    public Engagement_methods getEngagement_methods(int i) {
        return engagement_methods[i];
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public int getTelephone() {
        return telephone;
    }
}

