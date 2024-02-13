package com.mastercoding.bakalaurinis.retrofit;

import com.mastercoding.bakalaurinis.dtos.LoginResponse;
import com.mastercoding.bakalaurinis.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {
    @POST("api/users/register")
    Call<User> registerUser(@Body User user);

    @POST("api/users/login")
    Call<LoginResponse> loginUser(@Body User user);



}
