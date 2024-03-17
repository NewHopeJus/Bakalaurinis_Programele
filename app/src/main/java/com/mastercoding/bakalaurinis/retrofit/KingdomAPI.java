package com.mastercoding.bakalaurinis.retrofit;

import com.mastercoding.bakalaurinis.dtos.KingdomListsResponse;
import com.mastercoding.bakalaurinis.model.Question;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface KingdomAPI {
    @GET("/api/kingdoms/getKingdoms")
    Call<KingdomListsResponse> getKingdoms(@Header("Authorization") String jwtToken);
}
