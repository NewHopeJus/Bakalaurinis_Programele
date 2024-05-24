package com.mastercoding.bakalaurinis.security;

import android.content.Context;
import android.content.SharedPreferences;

public class MineSecurityManager {
    private static final String PREFERENCE_NAME = "MySharedPref";
    private static final String KEY_JWT_TOKEN = "jwt_token";
    private final SharedPreferences sharedPreferences;


    public MineSecurityManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_JWT_TOKEN, token); //The token needs to be included in every request, so it is saved in the mobile app's local storage.
        editor.apply();
    }

    public String getToken() {
        return "Bearer " + sharedPreferences.getString(KEY_JWT_TOKEN, "");
    }

    public void removeToken() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
