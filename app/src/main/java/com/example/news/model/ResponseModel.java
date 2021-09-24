package com.example.news.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModel {
    @SerializedName("status")
    private String status;

    @SerializedName("articles")
    private final List<Article> articles = null;

    public String getStatus() {
        return status;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
