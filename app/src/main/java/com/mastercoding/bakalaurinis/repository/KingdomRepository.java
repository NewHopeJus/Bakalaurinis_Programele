package com.mastercoding.bakalaurinis.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.mastercoding.bakalaurinis.dtos.KingdomListsResponse;
import com.mastercoding.bakalaurinis.retrofit.KingdomAPI;
import com.mastercoding.bakalaurinis.retrofit.RetrofitInstance;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class KingdomRepository {
    private KingdomAPI kingdomAPI;
    private MineSecurityManager securityManager;
    private MutableLiveData<KingdomListsResponse> kingdomListsResponseMutableLiveData;

    public KingdomRepository(MineSecurityManager securityManager) {
        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        kingdomAPI = retrofit.create(KingdomAPI.class);
        this.securityManager = securityManager;
        kingdomListsResponseMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<KingdomListsResponse> getKingdoms() {

        Call<KingdomListsResponse> call = kingdomAPI.getKingdoms(securityManager.getToken());
        call.enqueue(new Callback<KingdomListsResponse>() {

            @Override
            public void onResponse(@NonNull Call<KingdomListsResponse> call, @NonNull Response<KingdomListsResponse> response) {
                Log.e("Response body", response.toString());
                if (response.isSuccessful()) {
                    kingdomListsResponseMutableLiveData.setValue(response.body());

                } else {
                    Log.e("Kingdom Fetching Service", "Response was not successful");
                }
            }

            @Override
            public void onFailure(Call<KingdomListsResponse> call, Throwable t) {
                Log.e("Kingdom Fetching Service", "Getting kingdom from backend failed", t);
            }
        });

        return kingdomListsResponseMutableLiveData;
    }


}
