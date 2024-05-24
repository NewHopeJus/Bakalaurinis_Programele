package com.mastercoding.bakalaurinis.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;

    // IP address of the local machine where the server is running.
    // This IP is used during the development process.
    // Later, this IP should be changed to the IP address where the server is actually running in the production environment.
    private static final String baseUrl = "http://192.168.8.195:8080/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
