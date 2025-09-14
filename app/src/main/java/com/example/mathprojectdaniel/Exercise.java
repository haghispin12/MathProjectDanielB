package com.example.mathprojectdaniel;

import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class Exercise {
    private int score;
    private int answer;
    private Toast toaster;
    private int currentDiff;
    private ExerciseListener exerciseListener;
    public Exercise(MainActivity context, ExerciseListener EL){
        score = 0;
        answer = 0;
        currentDiff = 0;
        toaster = new Toast(context);
        toaster.setDuration(Toast.LENGTH_SHORT);
        exerciseListener = EL;
    }
    public int randInt(int num){
        Random random = new Random();
        return random.nextInt(num);
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
            score += 5 * currentDiff;
            exerciseListener.correct(score);
        }
        else {
            exerciseListener.incorrect();
        }
    }

}
