package com.example.mathprojectdaniel;

import android.widget.Toast;

import java.util.Random;

public class Exercise {
    private int answer;
    private int currentDiff;
    private ExerciseListener exerciseListener;
    private User user;
    public Exercise(ExerciseListener EL, User user){
        answer = 0;
        currentDiff = 0;
        exerciseListener = EL;
        this.user = user;
    }
    public int randInt(int num){
        Random random = new Random();
        return random.nextInt(num)+1;
    }
    public void easy() {
        int mana1 = randInt(9);
        int mana2 = randInt(9);
        answer = mana1 * mana2;
        currentDiff = 1;
        exerciseListener.setManas(mana1,mana2);
    }
    public void normal() {
        int mana1 = randInt(9);
        int mana2 = randInt(20);
        answer = mana1 * mana2;
        currentDiff = 2;
        exerciseListener.setManas(mana1,mana2);
    }
    public void hard() {
        int mana1 = randInt(9);
        int mana2 = randInt(99);
        answer = mana1 * mana2;
        currentDiff = 4;
        exerciseListener.setManas(mana1,mana2);
    }
    public void check(int userAnswer){
        if(answer == userAnswer){
            user.incScore(5 * currentDiff);
            exerciseListener.correct(user.getScore());
        }
        else {
            exerciseListener.incorrect();
        }
    }


}
