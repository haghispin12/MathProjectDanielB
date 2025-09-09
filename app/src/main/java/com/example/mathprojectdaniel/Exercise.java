package com.example.mathprojectdaniel;

import android.view.View;
import android.widget.Toast;

public class Exercise {
    private int score;
    private int answer;
    private Toast toaster;
    public Exercise(MainActivity context){
        score = 1;
        answer = 0;
        toaster = new Toast(context);
        toaster.setDuration(Toast.LENGTH_SHORT);
    }
    public int randInt(int num){
        return (int)(Math.random() * (num - 1) + 1);
    }
    public void easy() {
        int mana1 = randInt(9);
        int mana2 = randInt(9);
        answer = mana1 * mana2;
    }
    public void normal() {
        int mana1 = randInt(9);
        int mana2 = randInt(20);
        answer = mana1 * mana2;
    }
    public void hard() {
        int mana1 = randInt(9);
        int mana2 = randInt(99);
        answer = mana1 * mana2;
    }
    public void check(int trueAnswer){
        if(answer == trueAnswer){
            toaster.setText("Correct!");
            toaster.show();
        }
        else {
            toaster.setText("Wrong... Try Again.");
            toaster.show();
        }
    }
    public Interface ProductCallbackInterface{
        void showNumber(int number);
    }

}
