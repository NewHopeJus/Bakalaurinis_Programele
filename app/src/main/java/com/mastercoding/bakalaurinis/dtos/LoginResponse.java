package com.mastercoding.bakalaurinis.dtos;

public class LoginResponse {
    private String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public LoginResponse(String jwt) {
        this.jwt = jwt;
    }
}
