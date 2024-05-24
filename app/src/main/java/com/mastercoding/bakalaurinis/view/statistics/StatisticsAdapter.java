package com.mastercoding.bakalaurinis.view.statistics;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.dtos.LevelStatisticsDto;
import com.mastercoding.bakalaurinis.model.ShopItem;
import com.mastercoding.bakalaurinis.view.shop.ShopAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StatisticsAdapter extends RecyclerView.Adapter<StatisticsAdapter.StatisticsViewHolder> {

    private final List<LevelStatisticsDto> levelStatisticsDtoList;

    public StatisticsAdapter() {
        this.levelStatisticsDtoList = new ArrayList<>();
    }

    public void updateData(List<LevelStatisticsDto> newItems) {
        levelStatisticsDtoList.clear();
        levelStatisticsDtoList.addAll(newItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StatisticsAdapter.StatisticsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.statistics_layout, parent, false);
        return new StatisticsAdapter.StatisticsViewHolder(itemView);
    }

    public void clearData() {
        levelStatisticsDtoList.clear(); // Clear existing data
        notifyDataSetChanged(); // Notify adapter about the data change
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticsAdapter.StatisticsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        LevelStatisticsDto levelStatisticsDto = levelStatisticsDtoList.get(position);
        holder.levelName.setText(levelStatisticsDto.getLevelName());
        Integer incorrectAnswer = levelStatisticsDto.getTotalAnswered() - levelStatisticsDto.getLevelCorrectAnswered();
        holder.textViewLevelIncorrect.setText(String.valueOf(incorrectAnswer));
        holder.textViewLevelCorrect.setText(String.valueOf(levelStatisticsDto.getLevelCorrectAnswered()));

        String formattedAccuracy = "0";
        if (levelStatisticsDto.getTotalAnswered() != 0) {
            Double accuracy = (levelStatisticsDto.getLevelCorrectAnswered() * 100.0) / levelStatisticsDto.getTotalAnswered();
            formattedAccuracy = String.format(Locale.ENGLISH, "%.2f%%", accuracy);
        }
        holder.textViewTikslingumas.setText(formattedAccuracy);
    }

    @Override
    public int getItemCount() {
        return levelStatisticsDtoList.size();
    }

    public static class StatisticsViewHolder extends RecyclerView.ViewHolder {
        private final TextView levelName;
        private final TextView textViewLevelIncorrect;
        private final TextView textViewLevelCorrect;
        private final TextView textViewTikslingumas;

        public StatisticsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.levelName = itemView.findViewById(R.id.textViewLevelName);
            this.textViewLevelIncorrect = itemView.findViewById(R.id.textViewLevelInCorrect);
            this.textViewLevelCorrect = itemView.findViewById(R.id.textViewLevelCorrect);
            this.textViewTikslingumas = itemView.findViewById(R.id.textViewTikslingumas);
        }
    }
}
