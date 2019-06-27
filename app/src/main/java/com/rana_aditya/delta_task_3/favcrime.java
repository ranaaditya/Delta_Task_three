package com.rana_aditya.delta_task_3;

public class favcrime {
    private int id;
    private String category;


    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public favcrime(int id, String category) {
        this.id = id;
        this.category = category;
    }
}
