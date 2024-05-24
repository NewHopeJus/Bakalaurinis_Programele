package com.mastercoding.bakalaurinis.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.mastercoding.bakalaurinis.dtos.RankingDto;
import com.mastercoding.bakalaurinis.retrofit.RankingAPI;
import com.mastercoding.bakalaurinis.retrofit.RetrofitInstance;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RankingRepository {

    private RankingAPI rankingAPI;
    private MineSecurityManager securityManager;

    private MutableLiveData<List<RankingDto>> rankings;

    public RankingRepository(MineSecurityManager securityManager) {
        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        rankingAPI = retrofit.create(RankingAPI.class);
        this.securityManager = securityManager;
        rankings = new MutableLiveData<>();
    }

    public MutableLiveData<List<RankingDto>> getRankings() {
        return rankings;
    }

    public MutableLiveData<List<RankingDto>> getRankingList() {

        Call<List<RankingDto>> call = rankingAPI.getRanking(securityManager.getToken());
        call.enqueue(new Callback<List<RankingDto>>() {

            @Override
            public void onResponse(@NonNull Call<List<RankingDto>> call, @NonNull Response<List<RankingDto>> response) {
                Log.e("Response body", response.toString());
                if (response.isSuccessful()) {
                    rankings.setValue(response.body());

                } else {
                    Log.e("Statistics Fetching Repository", "Response for getting statistics from backend was not successful");
                }
            }

            @Override
            public void onFailure(Call<List<RankingDto>> call, Throwable t) {
                Log.e("Statistics Fetching Repository", "Getting statistics from backend failed", t);
            }
        });

        return rankings;
    }
}
