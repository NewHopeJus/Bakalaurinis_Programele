package com.mastercoding.bakalaurinis.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

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

    public ShopItemRepository(MineSecurityManager securityManager) {
        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        shopItemAPI = retrofit.create(ShopItemAPI.class);
        this.securityManager = securityManager;
        shopItemListMutableLiveData = new MutableLiveData<>();
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
}
