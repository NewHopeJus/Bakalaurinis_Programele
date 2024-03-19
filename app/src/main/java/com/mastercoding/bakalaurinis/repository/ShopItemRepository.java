package com.mastercoding.bakalaurinis.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mastercoding.bakalaurinis.dtos.BuyItemResponse;
import com.mastercoding.bakalaurinis.dtos.ShopItemListDto;
import com.mastercoding.bakalaurinis.retrofit.RetrofitInstance;
import com.mastercoding.bakalaurinis.retrofit.ShopItemAPI;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShopItemRepository {
    private ShopItemAPI shopItemAPI;
    private MineSecurityManager securityManager;
    private MutableLiveData<ShopItemListDto> shopItemListMutableLiveData;
    private MutableLiveData<BuyItemResponse> buyItemResponseMutableLiveData;

    private MutableLiveData<ShopItemListDto> boughtItemsForKingdomMutableLiveData;

    public ShopItemRepository(MineSecurityManager securityManager) {
        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        shopItemAPI = retrofit.create(ShopItemAPI.class);
        this.securityManager = securityManager;
        shopItemListMutableLiveData = new MutableLiveData<>();
        boughtItemsForKingdomMutableLiveData = new MutableLiveData<>();
        buyItemResponseMutableLiveData = new MutableLiveData<>();

    }

    public MutableLiveData<ShopItemListDto> getShopItemsForKingdom(Long kingdomId) {

        Call<ShopItemListDto> call = shopItemAPI.getItemsByKingdomId(kingdomId, securityManager.getToken());
        call.enqueue(new Callback<ShopItemListDto>() {

            @Override
            public void onResponse(@NonNull Call<ShopItemListDto> call, @NonNull Response<ShopItemListDto> response) {
                Log.e("Response body", response.body().toString());
                if (response.isSuccessful()) {
                    shopItemListMutableLiveData.setValue(response.body());

                } else {
                    Log.e("Shop Item  Repository", "Getting shop items from backend failed");
                }
            }

            @Override
            public void onFailure(Call<ShopItemListDto> call, Throwable t) {
                Log.e("Shop Item  Repository", "Getting shop items from backend failed", t);
            }
        });

        return shopItemListMutableLiveData;
    }

    public MutableLiveData<BuyItemResponse> buyItem(Long itemId) {

        Call<BuyItemResponse> call = shopItemAPI.buyItem(itemId, securityManager.getToken());
        call.enqueue(new Callback<BuyItemResponse>() {

            @Override
            public void onResponse(@NonNull Call<BuyItemResponse> call, @NonNull Response<BuyItemResponse> response) {
                Log.e("Response body", response.body().toString());
                if (response.isSuccessful()) {
                    buyItemResponseMutableLiveData.setValue(response.body());
                    Log.e("Shop Item  Repository", response.body().getMessage());


                } else {
                    Log.e("Shop Item  Repository", "Buying item failed");
                }
            }

            @Override
            public void onFailure(Call<BuyItemResponse> call, Throwable t) {
                Log.e("Shop Item  Repository", "Buying item failed", t);
            }
        });
        return buyItemResponseMutableLiveData;

    }

    public void getBoughtItemsByKingdomId(Long kingdomId) {

        Call<ShopItemListDto> call = shopItemAPI.getBoughtItemsForUserByKingdomId(kingdomId, securityManager.getToken());
        call.enqueue(new Callback<ShopItemListDto>() {

            @Override
            public void onResponse(@NonNull Call<ShopItemListDto> call, @NonNull Response<ShopItemListDto> response) {
                Log.e("Response body", response.body().toString());
                if (response.isSuccessful() && response.body() != null) {
                    boughtItemsForKingdomMutableLiveData.setValue(response.body());

                } else {
                    Log.e("Shop Item  Repository", "Getting bought items from backend failed");
                }
            }

            @Override
            public void onFailure(Call<ShopItemListDto> call, Throwable t) {
                Log.e("Shop Item  Repository", "Getting bought items from backend failed", t);
            }
        });
    }

    public LiveData<ShopItemListDto> getBoughtItemsLiveData() {
        return boughtItemsForKingdomMutableLiveData;
    }
}
