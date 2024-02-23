package com.mastercoding.bakalaurinis.repository;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.mastercoding.bakalaurinis.dtos.LoginRequest;
import com.mastercoding.bakalaurinis.dtos.LoginResponse;
import com.mastercoding.bakalaurinis.dtos.UserInfoResponse;
import com.mastercoding.bakalaurinis.model.User;
import com.mastercoding.bakalaurinis.retrofit.QuestionAPI;
import com.mastercoding.bakalaurinis.retrofit.RetrofitInstance;
import com.mastercoding.bakalaurinis.retrofit.UserAPI;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserRepository {
    private UserAPI userAPI;
    private MineSecurityManager securityManager;

    public UserRepository(MineSecurityManager mineSecurityManager) {
        this.userAPI = userAPI;
        this.securityManager = mineSecurityManager;
        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        userAPI = retrofit.create(UserAPI.class);
        this.securityManager = securityManager;
    }

    public MutableLiveData<LoginResponse> loginUser(LoginRequest loginRequest){
        MutableLiveData<LoginResponse> loginResponseMutableLiveData = new MutableLiveData<>();
        Call<LoginResponse> call = userAPI.loginUser(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null && !response.body().getJwt().isEmpty()) {
                        securityManager.saveToken(response.body().getJwt());
                        loginResponseMutableLiveData.setValue(response.body());

                    }
                }
                else {
                    Log.d("Login", "Login failed. Bad token. " + response.code());
                    loginResponseMutableLiveData.setValue(null);

                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                Log.d("Login", "Login failed " + t.getLocalizedMessage());
                loginResponseMutableLiveData.setValue(null);


            }
        });
        return loginResponseMutableLiveData;
    }

    public MutableLiveData<UserInfoResponse> getUserInfo(){
        MutableLiveData<UserInfoResponse> userInfoResponseMutableLiveData = new MutableLiveData<>();
        Call<UserInfoResponse> call = userAPI.getUserInfo(securityManager.getToken());
        call.enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserInfoResponse> call, Response<UserInfoResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        userInfoResponseMutableLiveData.setValue(response.body());
                    }
                }
                else {
                    Log.d("User Info", "Getting user info failed." + response.code());
                    userInfoResponseMutableLiveData.setValue(null);

                }
            }
            @Override
            public void onFailure(@NonNull Call<UserInfoResponse> call, @NonNull Throwable t) {
                Log.d("User Info", "Getting user info failed." + t.getLocalizedMessage());
                userInfoResponseMutableLiveData.setValue(null);

            }
        });
        return userInfoResponseMutableLiveData;
    }

}
