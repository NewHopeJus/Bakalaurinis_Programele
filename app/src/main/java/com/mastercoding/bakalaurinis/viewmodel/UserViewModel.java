package com.mastercoding.bakalaurinis.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mastercoding.bakalaurinis.dtos.LoginRequest;
import com.mastercoding.bakalaurinis.dtos.LoginResponse;
import com.mastercoding.bakalaurinis.dtos.UserInfoResponse;
import com.mastercoding.bakalaurinis.repository.UserRepository;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;

public class UserViewModel extends ViewModel {
    private MutableLiveData<LoginResponse> loginResponseMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<UserInfoResponse> userInfo = new MutableLiveData<>();

    private UserRepository userRepository;

    public UserViewModel(MineSecurityManager securityManager) {
        this.userRepository = new UserRepository(securityManager);
    }

    public MutableLiveData<LoginResponse> getLoginResponseMutableLiveData() {
        return loginResponseMutableLiveData;
    }

    public void setLoginResponseMutableLiveData(MutableLiveData<LoginResponse> loginResponseMutableLiveData) {
        this.loginResponseMutableLiveData = loginResponseMutableLiveData;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void loginUser(LoginRequest loginRequest) {
        loginResponseMutableLiveData = userRepository.loginUser(loginRequest);
    }

    public LoginResponse getLoginResponse() {
        return loginResponseMutableLiveData.getValue();
    }

    public void fetchUserInfo() {
        userInfo = userRepository.getUserInfo();
    }

    public MutableLiveData<UserInfoResponse> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(MutableLiveData<UserInfoResponse> userInfo) {
        this.userInfo = userInfo;
    }
}
