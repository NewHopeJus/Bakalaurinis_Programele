package com.mastercoding.bakalaurinis.view.ranking;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.dtos.LevelStatisticsDto;
import com.mastercoding.bakalaurinis.dtos.RankingDto;
import com.mastercoding.bakalaurinis.model.ShopItem;
import com.mastercoding.bakalaurinis.view.shop.ShopAdapter;

import java.util.ArrayList;
import java.util.List;

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankingViewHolder> {
    private List<RankingDto> itemsList;

    public RankingAdapter() {
        this.itemsList = new ArrayList<>();
    }

    public void updateData(List<RankingDto> newItems) {
        itemsList.clear();
        itemsList.addAll(newItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RankingAdapter.RankingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ranking_list_layout, parent, false);
        return new RankingAdapter.RankingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingAdapter.RankingViewHolder holder, @SuppressLint("RecyclerView") int position) {

        RankingDto rankingDto = itemsList.get(position);
        holder.textViewPlace.setText(String.valueOf(position + 1));
        holder.username.setText(String.valueOf(rankingDto.getUsername()));
        holder.correctlyAnswered.setText(String.valueOf(rankingDto.getCorrectlyAnsweredCount()));
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }


    public static class RankingViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPlace;
        TextView username;
        TextView correctlyAnswered;

        public RankingViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewPlace = itemView.findViewById(R.id.textViewPlace);
            this.username = itemView.findViewById(R.id.textViewNameRating);
            this.correctlyAnswered = itemView.findViewById(R.id.textViewCorrectlyAnsweredRating);
        }
    }
}
