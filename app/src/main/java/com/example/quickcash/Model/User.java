package com.example.quickcash.Model;

public class User {
    public String username, userType, email;

    public User(){

    }

    public User(String uName, String userType, String email){
        this.username = uName;
        this.userType = userType;
        this.email = email;
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

    public String getUserType() {
        return userType;
    }
}