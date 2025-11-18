package com.example.mathprojectdaniel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class User {
    private String name;
    private Long id;
    private int score;
    private float rating;
    private Uri profile;
    public User(String name){
        this.name = name;
        score = 0;
        rating = 0;
        id=1L;
        profile = null;
    }
    public User(Long id, String name, float rating, Uri profile, int score){

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
