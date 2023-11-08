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

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("role")
    @Expose
    private String role;

    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
