package com.mastercoding.bakalaurinis.retrofit;

import com.mastercoding.bakalaurinis.dtos.BuyItemResponse;
import com.mastercoding.bakalaurinis.dtos.ShopItemListDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ShopItemAPI {
    @GET("/api/items/{id}")
    Call<ShopItemListDto> getItemsByKingdomId(@Path("id") long id, @Header("Authorization") String jwtToken);

    @POST("/api/items/buy/{id}")
    Call<BuyItemResponse> buyItem(@Path("id") long id, @Header("Authorization") String jwtToken);

    @GET("/api/items/getBoughtItems/{id}")
    Call<ShopItemListDto> getBoughtItemsForUserByKingdomId(@Path("id") long id, @Header("Authorization") String jwtToken);
}
