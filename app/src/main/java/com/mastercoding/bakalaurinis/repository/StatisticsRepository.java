package com.mastercoding.bakalaurinis.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.mastercoding.bakalaurinis.dtos.KingdomListsResponse;
import com.mastercoding.bakalaurinis.dtos.ShopItemListDto;
import com.mastercoding.bakalaurinis.dtos.StatisticsResponse;
import com.mastercoding.bakalaurinis.retrofit.KingdomAPI;
import com.mastercoding.bakalaurinis.retrofit.RetrofitInstance;
import com.mastercoding.bakalaurinis.retrofit.StatisticsAPI;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StatisticsRepository {
    StatisticsAPI statisticsAPI;
    private MutableLiveData<StatisticsResponse> statisticsMutableLiveData;

    private MineSecurityManager securityManager;

    public StatisticsRepository(MineSecurityManager securityManager) {
        this.securityManager = securityManager;
        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        statisticsAPI = retrofit.create(StatisticsAPI.class);
        this.securityManager = securityManager;
        statisticsMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<StatisticsResponse> getStatisticsMutableLiveData() {
        return statisticsMutableLiveData;
    }

    public MutableLiveData<StatisticsResponse> getStatistics() {

        Call<StatisticsResponse> call = statisticsAPI.getStatistics(securityManager.getToken());
        call.enqueue(new Callback<StatisticsResponse>() {

            @Override
            public void onResponse(@NonNull Call<StatisticsResponse> call, @NonNull Response<StatisticsResponse> response) {
                Log.e("Response body", response.toString());
                if (response.isSuccessful()) {
                    statisticsMutableLiveData.setValue(response.body());

                } else {
                    Log.e("Statistics Fetching Repository", "Response for getting statistics from backend was not successful");
                }
            }

            @Override
            public void onFailure(Call<StatisticsResponse> call, Throwable t) {
                Log.e("Statistics Fetching Repository", "Getting statistics from backend failed", t);
            }
        });
        return statisticsMutableLiveData;
    }

}
