package com.example.news.util;

import com.example.news.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiResponse {
    @GET("top-headlines")
    Call<ResponseModel> getCategoryHeadlines(@Query("country") String country,
                                             @Query("category") String category,
                                             @Query("apiKey") String apiKey);
}
