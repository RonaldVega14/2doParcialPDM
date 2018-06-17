package com.vega.gamenews.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vega.gamenews.Database.Entities.PlayerEntity;
import com.vega.gamenews.R;

import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder>{

    private List<PlayerEntity> playerEntities;
    private Context context;

    public PlayersAdapter(Context context) {
        this.context = context;
    }

    public void setPlayers(List<PlayerEntity> players){

        playerEntities = players;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.players_cardview, parent, false);

        return (new PlayerViewHolder(v));
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {

        Picasso.with(context).load(playerEntities.get(position).getAvatar()).error(R.drawable.profile).into(holder.imageView);
        holder.textView.setText(playerEntities.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return playerEntities == null ? 0 : playerEntities.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;


        public PlayerViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.photo);
            textView = itemView.findViewById(R.id.name);
        }
    }
}
