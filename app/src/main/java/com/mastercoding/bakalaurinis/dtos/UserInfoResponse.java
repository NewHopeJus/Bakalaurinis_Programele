package com.mastercoding.bakalaurinis.dtos;

public class UserInfoResponse {
    private Integer userExperience;
    private Integer userCoins;
    private String username;

    public Integer getUserExperience() {
        return userExperience;
    }

    public void setUserExperience(Integer userExperience) {
        this.userExperience = userExperience;
    }

    public Integer getUserCoins() {
        return userCoins;
    }

    public void setUserCoins(Integer userCoins) {
        this.userCoins = userCoins;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
