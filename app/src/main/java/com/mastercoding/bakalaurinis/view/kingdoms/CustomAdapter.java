package com.mastercoding.bakalaurinis.view.kingdoms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mastercoding.bakalaurinis.R;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.KingdomViewHolder> {

    private List<Kingdom> kingdomsList;
    private ItemClickListener itemClickListener;
    public CustomAdapter(List<Kingdom> kingdomsList, ItemClickListener itemClickListener) {
        this.kingdomsList = kingdomsList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public KingdomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflating the layout for each item in the recyclerview

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kingdom_list_card_item_layout, parent, false);

        return new KingdomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull KingdomViewHolder holder, final int position) {
        //called for each item in the list and is responsible for binding the data from the Kingdom object to the views
        //within the 'Kingdom ViewHolder'
        Kingdom kingdom = kingdomsList.get(position);
        holder.textView.setText(kingdom.getKingdomName());
        holder.imageView.setImageResource(kingdom.getKingdomImg());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener!=null) {
                    itemClickListener.onItemClick(position);
                }
            }
        });

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

    public interface ItemClickListener{
        public void onItemClick(int position);
    }
}
