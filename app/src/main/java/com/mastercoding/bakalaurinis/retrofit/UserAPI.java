package com.mastercoding.bakalaurinis.retrofit;

import com.mastercoding.bakalaurinis.dtos.AccountDeleteRequest;
import com.mastercoding.bakalaurinis.dtos.LoginRequest;
import com.mastercoding.bakalaurinis.dtos.LoginResponse;
import com.mastercoding.bakalaurinis.dtos.PasswordChangeRequest;
import com.mastercoding.bakalaurinis.dtos.UserInfoResponse;
import com.mastercoding.bakalaurinis.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    @POST("api/users/update/username")
    Call<LoginResponse> updateUsername(@Body LoginRequest loginRequest, @Header("Authorization") String jwtToken);

    @POST("api/users/update/password")
    Call<LoginResponse> updatePassword(@Body PasswordChangeRequest passwordChangeRequest, @Header("Authorization") String jwtToken);

    @POST("api/users/delete")
    Call<ResponseBody> deleteAccount(@Header("Authorization") String jwtToken, @Body AccountDeleteRequest deleteAccountRequest);
}
