package com.mastercoding.bakalaurinis.retrofit;

import com.mastercoding.bakalaurinis.dtos.LoginRequest;
import com.mastercoding.bakalaurinis.dtos.LoginResponse;
import com.mastercoding.bakalaurinis.dtos.UserInfoResponse;
import com.mastercoding.bakalaurinis.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserAPI {
    @POST("api/users/register")
    Call<LoginRequest> registerUser(@Body LoginRequest loginRequest);

    @POST("api/users/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @GET("api/users/userInfo")
    Call<UserInfoResponse> getUserInfo(@Header("Authorization") String jwtToken);



}
