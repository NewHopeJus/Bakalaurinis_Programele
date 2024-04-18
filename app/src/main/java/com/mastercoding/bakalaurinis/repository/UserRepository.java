package com.mastercoding.bakalaurinis.repository;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.mastercoding.bakalaurinis.dtos.AccountDeleteRequest;
import com.mastercoding.bakalaurinis.dtos.LoginRequest;
import com.mastercoding.bakalaurinis.dtos.LoginResponse;
import com.mastercoding.bakalaurinis.dtos.PasswordChangeRequest;
import com.mastercoding.bakalaurinis.dtos.UserInfoResponse;
import com.mastercoding.bakalaurinis.model.User;
import com.mastercoding.bakalaurinis.retrofit.QuestionAPI;
import com.mastercoding.bakalaurinis.retrofit.RetrofitInstance;
import com.mastercoding.bakalaurinis.retrofit.UserAPI;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserRepository {
    private UserAPI userAPI;
    private MineSecurityManager securityManager;
    private MutableLiveData<LoginResponse> loginResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<LoginResponse> updateResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<UserInfoResponse> userInfoResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<LoginResponse> updatePasswordResponseMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<String> deleteAccountResponse = new MutableLiveData<>();
    public UserRepository(MineSecurityManager mineSecurityManager) {
        this.securityManager = mineSecurityManager;
        Retrofit retrofit = RetrofitInstance.getRetrofitInstance();
        userAPI = retrofit.create(UserAPI.class);
        this.securityManager = mineSecurityManager;
    }

    public MutableLiveData<LoginResponse> loginUser(LoginRequest loginRequest){
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
        Call<UserInfoResponse> call = userAPI.getUserInfo(securityManager.getToken());
        call.enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserInfoResponse> call, Response<UserInfoResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        userInfoResponseMutableLiveData.postValue(response.body());
                    }
                }
                else {
                    Log.d("User Info", "Getting user info failed." + response.code());
                    userInfoResponseMutableLiveData.postValue(null);

                }
            }
            @Override
            public void onFailure(@NonNull Call<UserInfoResponse> call, @NonNull Throwable t) {
                Log.d("User Info", "Getting user info failed." + t.getLocalizedMessage());
                userInfoResponseMutableLiveData.postValue(null);

            }
        });
        return userInfoResponseMutableLiveData;
    }

    public MutableLiveData<LoginResponse> updateUsername(LoginRequest loginRequest){
        Call<LoginResponse> call = userAPI.updateUsername(loginRequest, securityManager.getToken());

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null && !response.body().getJwt().isEmpty()) {

                        updateResponseMutableLiveData.setValue(response.body());
                        securityManager.removeToken();
                        securityManager.saveToken(response.body().getJwt());

                    }
                }
                else {
                    Log.d("Login", "Username update failed. " + response.code());
                    updateResponseMutableLiveData.setValue(null);

                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                Log.d("Login", "Username update failed. " + t.getLocalizedMessage());
                updateResponseMutableLiveData.setValue(null);

            }
        });
        return updateResponseMutableLiveData;
    }

    public MutableLiveData<LoginResponse> updatePassword(PasswordChangeRequest passwordChangeRequest){
        Call<LoginResponse> call = userAPI.updatePassword(passwordChangeRequest, securityManager.getToken());

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null && !response.body().getJwt().isEmpty()) {

                        updatePasswordResponseMutableLiveData.setValue(response.body());
                        securityManager.removeToken();
                        securityManager.saveToken(response.body().getJwt());

                    }
                }
                else {
                    Log.d("Login", "Username update failed. " + response.code());
                    updatePasswordResponseMutableLiveData.setValue(null);

                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                Log.d("Login", "Username update failed. " + t.getLocalizedMessage());
                updatePasswordResponseMutableLiveData.setValue(null);

            }
        });
        return updatePasswordResponseMutableLiveData;
    }


    public MutableLiveData<String> deleteUser(AccountDeleteRequest accountDeleteRequest) {
        Call<ResponseBody> call = userAPI.deleteAccount(securityManager.getToken(), accountDeleteRequest);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        deleteAccountResponse.setValue(response.body().toString());

                    }
                } else {
                    Log.d("Delete", "Delete account failed" + response.code());
                    deleteAccountResponse.setValue(null);

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Delete", "Delete account failed");
                deleteAccountResponse.setValue(null);
            }


        });

        return deleteAccountResponse;
    }

    public MutableLiveData<LoginResponse> getLoginResponseMutableLiveData() {
        return loginResponseMutableLiveData;
    }

    public MutableLiveData<LoginResponse> getUpdateResponseMutableLiveData() {
        return updateResponseMutableLiveData;
    }

    public MutableLiveData<UserInfoResponse> getUserInfoResponseMutableLiveData() {
        return userInfoResponseMutableLiveData;
    }

    public MutableLiveData<LoginResponse> getUpdatePasswordResponseMutableLiveData() {
        return updatePasswordResponseMutableLiveData;
    }

    public MutableLiveData<String> getDeleteAccountResponse() {
        return deleteAccountResponse;
    }
}
