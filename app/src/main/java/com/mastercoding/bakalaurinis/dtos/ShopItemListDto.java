package com.mastercoding.bakalaurinis.dtos;

import com.mastercoding.bakalaurinis.model.ShopItem;

import java.util.List;

public class ShopItemListDto {
    private List<ShopItem> shopItems;

    public ShopItemListDto(List<ShopItem> shopItemList) {
        this.shopItems = shopItemList;
    }

    public List<ShopItem> getShopItems() {
        return shopItems;
    }
}
