package com.mastercoding.bakalaurinis.retrofit;

import com.mastercoding.bakalaurinis.dtos.StatisticsResponse;
import com.mastercoding.bakalaurinis.dtos.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface StatisticsAPI {
    @GET("api/statistics")
    Call<StatisticsResponse> getStatistics(@Header("Authorization") String jwtToken);
}
