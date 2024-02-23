package com.mastercoding.bakalaurinis.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//Specifically, @Expose is used to indicate whether a field
// should be included in the JSON serialization and deserialization process.
// Fields annotated with @Expose are considered as "exposed,"
// meaning they will be processed by Gson when converting between Java objects
// and JSON,
// while fields without this annotation are typically ignored.

public class User {

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;

    private Integer userExperience;
    private Integer userCoins;

    public User(String username, String password, Integer userExperience, Integer userCoins) {
        this.username = username;
        this.password = password;
        this.userExperience = userExperience;
        this.userCoins = userCoins;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
}
