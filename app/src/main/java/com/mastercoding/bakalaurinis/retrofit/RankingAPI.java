package com.mastercoding.bakalaurinis.retrofit;

import com.mastercoding.bakalaurinis.dtos.RankingDto;
import com.mastercoding.bakalaurinis.dtos.StatisticsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface RankingAPI {
    @GET("api/ranking")
    Call<List<RankingDto>> getRanking(@Header("Authorization") String jwtToken);
}
