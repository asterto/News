package com.example.news.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {
    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }
}
