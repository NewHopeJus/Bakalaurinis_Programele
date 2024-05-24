package com.mastercoding.bakalaurinis.view.kingdoms;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mastercoding.bakalaurinis.R;
import com.mastercoding.bakalaurinis.dtos.KingdomDto;
import com.mastercoding.bakalaurinis.model.Kingdom;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.KingdomViewHolder> {
    private List<KingdomDto> kingdomsList;
    private ItemClickListener itemClickListener;

    public CustomAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        kingdomsList = new ArrayList<>();
    }

    public void updateData(List<KingdomDto> newItems) {
        kingdomsList.clear();
        kingdomsList.addAll(newItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KingdomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kingdom_list_card_item_layout, parent, false);
        return new KingdomViewHolder(itemView);
    }

    // Called for each item in the list. This method is responsible for binding the data from the
    // Kingdom object to the views defined in the KingdomViewHolder.
    @Override
    public void onBindViewHolder(@NonNull KingdomViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        KingdomDto kingdomDto = kingdomsList.get(position);
        if (kingdomDto != null) {
            if (!kingdomDto.getImg().isEmpty()) {
                String imageName = kingdomDto.getImg();
                int imageResId = holder.itemView.getContext().getResources().getIdentifier(imageName, "drawable", holder.itemView.getContext().getPackageName());
                if (imageResId != 0) {
                    holder.imageView.setImageResource(imageResId);
                }
            }
            holder.textView.setText(kingdomDto.getName());
            if (!kingdomsList.get(position).isOpened()) {
                ColorMatrix colorMatrix = new ColorMatrix();
                colorMatrix.setSaturation(0f);
                ColorMatrixColorFilter colorFilter =
                        new ColorMatrixColorFilter(colorMatrix);
                holder.imageView.setColorFilter(colorFilter);
                CardView cardView = holder.itemView.findViewById(R.id.cardview_kingdom);
                cardView.setCardBackgroundColor(Color.GRAY);
            }
            if (kingdomDto.isOpened()) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (itemClickListener != null) {
                            itemClickListener.onItemClick(position);
                        }
                    }
                });
            } else {
                // Disabling clicks for the closed kingdoms
                holder.itemView.setOnClickListener(null);
            }
        }
    }

    @Override
    public int getItemCount() {
        return kingdomsList.size();
    }

    public static class KingdomViewHolder extends RecyclerView.ViewHolder {
        //Holds te references to the views within the item layout
        TextView textView;
        ImageView imageView;

        public KingdomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textViewKingdomCard);
            this.imageView = itemView.findViewById(R.id.imageviewKingdomCard);
        }
    }

    public interface ItemClickListener {
        public void onItemClick(int position);
    }

    public List<KingdomDto> getKingdomsList() {
        return kingdomsList;
    }

    public void setKingdomsList(List<KingdomDto> kingdomsList) {
        this.kingdomsList = kingdomsList;
    }
}
