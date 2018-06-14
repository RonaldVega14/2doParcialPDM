package com.vega.gamenews.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vega.gamenews.Models.News;
import com.vega.gamenews.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private Context context;
    private List<News> news;
    private boolean favo;


    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView title, description;
        ImageView image;
        boolean fav;

        public NewsViewHolder(View itemView, boolean fav) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            image  = itemView.findViewById(R.id.image);
            this.fav = fav;
        }
    }

    public void setNews(List<News> news){
        this.news = news;
        notifyDataSetChanged();
    }

    public NewsAdapter (Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.news_cardview, parent, false);
        return (new NewsViewHolder(v, this.favo));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.title.setText(news.get(position).getTitle());
        holder.description.setText(news.get(position).getTitle());
        Picasso.with(context).load(news.get(position).getCoverImage()).error(R.drawable.hola).into(holder.image);


    }

    @Override
    public int getItemCount() {
        if (news == null) {
            return 0;
        }

        return news.size();
    }
}
