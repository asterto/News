package com.example.news.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.model.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainArticleAdapter extends RecyclerView.Adapter<MainArticleAdapter.ViewHolder> {
    private final List<Article> articleArrayList;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public MainArticleAdapter(List<Article> articleArrayList) {
        this.articleArrayList = articleArrayList;
    }

    @NonNull
    @Override
    public MainArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_main_article_adapter, viewGroup, false);
        return new MainArticleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainArticleAdapter.ViewHolder viewHolder, int position) {
        final Article articleModel = articleArrayList.get(position);
        if (!TextUtils.isEmpty(articleModel.getTitle())) {
            viewHolder.title.setText(articleModel.getTitle());
        }
        if (!TextUtils.isEmpty(articleModel.getSource().getName())) {
            viewHolder.sourceName.setText(articleModel.getSource().getName());
        }
        if (!TextUtils.isEmpty(articleModel.getPublishedAt())) {
            viewHolder.date.setText(articleModel.getPublishedAt());
        }
        if (!TextUtils.isEmpty(articleModel.getUrlToImage())) {
            Picasso.get()
                    .load(articleModel.getUrlToImage())
                    .resize(100, 100)
                    .centerCrop()
                    .into(viewHolder.imgThumbnail);
        }

        viewHolder.mainArticleAdapter.setTag(articleModel);
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title, sourceName, date;
        private final ImageView imgThumbnail;
        private final LinearLayout mainArticleAdapter;

        ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            sourceName = view.findViewById(R.id.source_name);
            date = view.findViewById(R.id.date);
            imgThumbnail = view.findViewById(R.id.img_thumbnail);
            mainArticleAdapter = view.findViewById(R.id.main_article_adapter);
            mainArticleAdapter.setOnClickListener(view1 -> {
                if (onRecyclerViewItemClickListener != null) {
                    onRecyclerViewItemClickListener.onItemClick(getAdapterPosition(), view1);
                }
            });
        }

    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}
