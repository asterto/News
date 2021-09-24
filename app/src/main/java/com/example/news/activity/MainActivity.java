package com.example.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.adapter.MainArticleAdapter;
import com.example.news.adapter.OnRecyclerViewItemClickListener;
import com.example.news.model.Article;
import com.example.news.model.ResponseModel;
import com.example.news.util.ApiClient;
import com.example.news.util.ApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.news.util.Constants.API_KEY;
import static com.example.news.util.Constants.CATEGORY_BUSINESS;
import static com.example.news.util.Constants.COUNTRY;

public class MainActivity extends AppCompatActivity implements OnRecyclerViewItemClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView mainRecycler = findViewById(R.id.activity_main_rv);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mainRecycler.setLayoutManager(linearLayoutManager);

        final ApiResponse ApiResponse = ApiClient.getApiService();
        Call<ResponseModel> call = ApiResponse.getCategoryHeadlines(COUNTRY, CATEGORY_BUSINESS, API_KEY);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<ResponseModel> call, @NonNull Response<ResponseModel> response) {
                if (response.body().getStatus().equals("ok") && response.body().getArticles() != null) {
                    List<Article> articleList = response.body().getArticles();
                    if (articleList.size() > 0) {
                        final MainArticleAdapter mainArticleAdapter = new MainArticleAdapter(articleList);
                        mainArticleAdapter.setOnRecyclerViewItemClickListener(MainActivity.this);
                        mainRecycler.setAdapter(mainArticleAdapter);
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseModel> call, @NonNull Throwable t) {
                Log.e("out", t.toString());
                Toast.makeText(MainActivity.this, "Internet is not connected", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemClick(int adapterPosition, View view) {
        if (view.getId() == R.id.main_article_adapter) {
            Article article = (Article) view.getTag();
            if (!TextUtils.isEmpty(article.getUrl())) {
                Log.e("clicked url", article.getUrl());
                Intent webActivity = new Intent(this, WebActivity.class);
                webActivity.putExtra("url", article.getUrl());
                startActivity(webActivity);
            }
        }
    }

}