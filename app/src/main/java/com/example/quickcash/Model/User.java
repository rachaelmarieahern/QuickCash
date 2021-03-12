package com.example.quickcash.Model;

public class User {
    public String Username, userType, email;

    public User(){

    }

    public User(String uName, String userType, String email){
        this.Username = uName;
        this.userType = userType;
        this.email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
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