package com.example.mathprojectdaniel;

public class User {
    private String name;
    private int score;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incScore(int num){
        score += num;
    }

    @Override
    public String toString(){
        return "name: " + name + " score: " + score;
    }
}
