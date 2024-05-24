package com.mastercoding.bakalaurinis.view.shop;

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
import com.mastercoding.bakalaurinis.model.ShopItem;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder> {

    private List<ShopItem> itemsList;
    private ShopAdapter.ShopItemClickListener itemClickListener;

    public ShopAdapter(ShopAdapter.ShopItemClickListener itemClickListener) {
        this.itemsList = new ArrayList<>();
        this.itemClickListener = itemClickListener;
    }

    public void updateData(List<ShopItem> newItems) {
        itemsList.clear();
        itemsList.addAll(newItems);
        notifyDataSetChanged();
    }

    public void clearData() {
        itemsList.clear(); // Clear existing data
        notifyDataSetChanged(); // Notify adapter about the data change
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shop_list_item_layout, parent, false);
        return new ShopAdapter.ShopViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ShopItem shopItem = itemsList.get(position);
        holder.textViewItemName.setText(shopItem.getName());
        holder.textViewPrice.setText(String.valueOf(shopItem.getPrice()));

        if (!shopItem.getImgName().isEmpty()) {
            String imageName = shopItem.getImgName();
            int imageResId = holder.itemView.getContext().getResources().getIdentifier(imageName, "drawable", holder.itemView.getContext().getPackageName());
            if (imageResId != 0) {
                holder.imageViewItemImage.setImageResource(imageResId);
            }
        }
        //Listening to button clicks
        holder.buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public static class ShopViewHolder extends RecyclerView.ViewHolder {
        //Holds te references to the views within the item layout
        private final ImageView imageViewItemImage;
        private final TextView textViewItemName;
        private final Button buttonBuy;
        private final TextView textViewPrice;

        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewItemName = itemView.findViewById(R.id.textViewItemName);
            this.imageViewItemImage = itemView.findViewById(R.id.imageViewItemImage);
            this.buttonBuy = itemView.findViewById(R.id.buttonBuy);
            this.textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }
    }

    public interface ShopItemClickListener {
        public void onItemClick(int position);
    }
}
