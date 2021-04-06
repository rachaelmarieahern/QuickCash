package com.example.quickcash.Model;

public class User {
    public String username, userType, email;
    public double sumOfRatings;
    public float avgRating;
    public int numOfRatings;
    public String token;

    public User(){
    }

    public User(String uName, String userType, String email, String token){
        this.username = uName;
        this.userType = userType;
        this.email = email;
        this.avgRating = 5;
        this.numOfRatings = 0;
        this.sumOfRatings = 0;
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

    public double getSumOfRatings() {
        return sumOfRatings;
    }

    public void setSumOfRatings(double sumOfRatings) {
        this.sumOfRatings = sumOfRatings;
    }

    public double getAvgRating() {
        return (sumOfRatings/numOfRatings);
    }

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

}
