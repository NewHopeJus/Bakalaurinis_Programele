package com.mastercoding.bakalaurinis.security;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityManager {
    private static final String PREFERENCE_NAME = "MySharedPref";
    private static final String KEY_JWT_TOKEN = "jwt_token";
    private final SharedPreferences sharedPreferences;


    public SecurityManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_JWT_TOKEN, token); //nes reikes tokena deti i kiekviena requesta todel ji issaugom
        editor.apply();

    }

    public String getToken() {
        return "Bearer " + sharedPreferences.getString(KEY_JWT_TOKEN, "");
    }

}
