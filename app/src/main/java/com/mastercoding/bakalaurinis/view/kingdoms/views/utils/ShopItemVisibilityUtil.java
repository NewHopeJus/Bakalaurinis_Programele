package com.mastercoding.bakalaurinis.view.kingdoms.views.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.mastercoding.bakalaurinis.dtos.ShopItemListDto;
import com.mastercoding.bakalaurinis.model.ShopItem;

public class ShopItemVisibilityUtil {
    public static void setBoughtItemsVisibility(View view, ShopItemListDto shopItemListDto, Context context) {
        if (shopItemListDto != null) {
            for (ShopItem shopItem : shopItemListDto.getShopItems()) {
                String imgViewName = shopItem.getImgViewId();
                int resId = context.getResources().getIdentifier(imgViewName, "id", context.getPackageName());
                if (resId != 0) {
                    View imageView = view.findViewById(resId);
                    if (imageView instanceof ImageView) {
                        imageView.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
}
