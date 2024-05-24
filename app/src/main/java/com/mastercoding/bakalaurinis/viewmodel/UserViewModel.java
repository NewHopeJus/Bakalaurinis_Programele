package com.mastercoding.bakalaurinis.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mastercoding.bakalaurinis.dtos.AccountDeleteRequest;
import com.mastercoding.bakalaurinis.dtos.LoginRequest;
import com.mastercoding.bakalaurinis.dtos.LoginResponse;
import com.mastercoding.bakalaurinis.dtos.PasswordChangeRequest;
import com.mastercoding.bakalaurinis.dtos.UserInfoResponse;
import com.mastercoding.bakalaurinis.repository.UserRepository;
import com.mastercoding.bakalaurinis.security.MineSecurityManager;

public class UserViewModel extends ViewModel {
    private MutableLiveData<LoginResponse> loginResponseMutableLiveData;
    private MutableLiveData<UserInfoResponse> userInfo;
    private MutableLiveData<LoginResponse> updateResponseMutableLiveData;

    private MutableLiveData<LoginResponse> updatePasswordResponseMutableLiveData;
    private MutableLiveData<String> deleteAccountMutableLiveData;

    private UserRepository userRepository;

    public UserViewModel(MineSecurityManager securityManager) {
        this.userRepository = new UserRepository(securityManager);
        loginResponseMutableLiveData = userRepository.getLoginResponseMutableLiveData();
        updateResponseMutableLiveData = userRepository.getUpdateResponseMutableLiveData();
        userInfo = userRepository.getUserInfoResponseMutableLiveData();
        updatePasswordResponseMutableLiveData = userRepository.getUpdatePasswordResponseMutableLiveData();
        deleteAccountMutableLiveData = userRepository.getDeleteAccountResponse();
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
        userRepository.loginUser(loginRequest);
    }

    public LoginResponse getLoginResponse() {
        return loginResponseMutableLiveData.getValue();
    }

    public void fetchUserInfo() {
        userRepository.getUserInfo();
    }

    public MutableLiveData<UserInfoResponse> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(MutableLiveData<UserInfoResponse> userInfo) {
        this.userInfo = userInfo;
    }

    public void updateUsername(LoginRequest loginRequest) {
        userRepository.updateUsername(loginRequest);
    }

    public void deleteAccount(AccountDeleteRequest password) {
        userRepository.deleteUser(password);
    }

    public MutableLiveData<LoginResponse> getUpdateResponseMutableLiveData() {
        return updateResponseMutableLiveData;
    }

    public void updatePassword(PasswordChangeRequest passwordChangeRequest) {
        userRepository.updatePassword(passwordChangeRequest);
    }

    public MutableLiveData<LoginResponse> getUpdatePasswordResponseMutableLiveData() {
        return updatePasswordResponseMutableLiveData;
    }

    public MutableLiveData<String> getDeleteAccountMutableLiveData() {
        return deleteAccountMutableLiveData;
    }
}
