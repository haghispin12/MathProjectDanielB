package com.example.mathprojectdaniel;

import android.widget.ImageView;

public class User {
    private String name;
    private int score;
    private float rating;
    private ImageView profile;
    public User(String name){
        this.name = name;
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public float getRating() {return rating;}

    public ImageView getProfile() {return profile;}

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setRating(float rating) {this.rating = rating;}

    public void setProfile(ImageView profile) {this.profile = profile;}

    public void incScore(int num){
        score += num;
    }

    @Override
    public String toString(){
        return "name: " + name + " score: " + score;
    }
}
