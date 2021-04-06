package com.example.quickcash.Model;

public class User {
    private String username;
    private String userType;
    private String email;
    private String token;
    private float sumOfRatings;
    private float avgRating;
    private int numOfRatings;

    public User(){
    }

    public User(String uName, String userType, String email, float avgRating, int numOfRatings, float sumOfRatings, String token){
        this.username = uName;
        this.userType = userType;
        this.email = email;
        this.avgRating = avgRating;
        this.numOfRatings = numOfRatings;
        this.sumOfRatings = sumOfRatings;
        this.token = token;
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

    public float getSumOfRatings() {
        return sumOfRatings;
    }

    public void setSumOfRatings(float sumOfRatings) {
        this.sumOfRatings = sumOfRatings;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) { this.avgRating = avgRating; }

    public int getNumOfRatings() {
        return numOfRatings;
    }

    public void setNumOfRatings(int numOfRatings) {
        this.numOfRatings = numOfRatings;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

}
