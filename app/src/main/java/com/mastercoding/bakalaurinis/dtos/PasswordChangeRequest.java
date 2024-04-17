package com.mastercoding.bakalaurinis.dtos;

public class PasswordChangeRequest {
   private String oldPassword;
    private String newPassword;

    public PasswordChangeRequest(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
