package com.example.mathprojectdaniel;

import android.graphics.Bitmap;
import android.net.Uri;

public class User {
    private String name;
    private Long id;
    private int score;
    private float rating;
    private Uri profile;
    private Bitmap ProfileBitmap;
    public User(String name){
        this.name = name;
        score = 0;
        rating = 0;
        id=1L;
        profile = null;
    }
    public User(Long id, String name, float rating, Bitmap profile, int score){
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.ProfileBitmap = profile;
        this.score = score;
    }
    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public float getRating() {return rating;}

    public Uri getProfile() {return profile;}
    public Bitmap getProfileBitmap() {return ProfileBitmap;}

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setRating(float rating) {this.rating = rating;}

    public void setProfile(Uri profile) {this.profile = profile;}

    public void incScore(int num){
        score += num;
    }

    @Override
    public String toString(){
        return "name: " + name + " score: " + score;
    }
}
