package com.mastercoding.bakalaurinis.retrofit;

import com.mastercoding.bakalaurinis.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @POST("api/users/add")
    Call<User> addUser(@Body User user);


}
