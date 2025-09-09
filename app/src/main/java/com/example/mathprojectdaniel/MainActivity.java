package com.example.mathprojectdaniel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView scoreTV;
    private Button easyBtn;
    private Button normalBtn;
    private Button hardBtn;
    private TextView mana1TV;
    private TextView mana2TV;
    private EditText answerET;
    private  Button checkBtn;
    private Button saveBtn;
    private Button showAllUsersBtn;
    private int answer;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        CreateOnClickListeners();
    }
    public int randInt(int num){
        return (int)(Math.random() * (num - 1) + 1);
    }
    public void init(){
        scoreTV = findViewById(R.id.score);
        easyBtn = findViewById(R.id.easy);
        normalBtn = findViewById(R.id.normal);
        hardBtn = findViewById(R.id.hard);
        mana1TV = findViewById(R.id.mana1);
        mana2TV = findViewById(R.id.mana2);
        answerET = findViewById(R.id.answer);
        checkBtn = findViewById(R.id.check);
        saveBtn = findViewById(R.id.save);
        showAllUsersBtn = findViewById(R.id.showAllUsers);
        answer = 0;
        score = 1;
    }
    public void CreateOnClickListeners(){
        easyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mana1 = randInt(9);
                int mana2 = randInt(9);
                answer = mana1 * mana2;
                mana1TV.setText(Integer.toString(mana1));
                mana2TV.setText(Integer.toString(mana2));
                checkBtn.setEnabled(true);
                answerET.setText("");
            }
        });
        normalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mana1 = randInt(9);
                int mana2 = randInt(20);
                answer = mana1 * mana2;
                mana1TV.setText(mana1+"");
                mana2TV.setText(Integer.toString(mana2));
                checkBtn.setEnabled(true);
                answerET.setText("");
            }
        });
        hardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mana1 = randInt(9);
                int mana2 = randInt(99);
                answer = mana1 * mana2;
                mana1TV.setText(Integer.toString(mana1));
                mana2TV.setText(Integer.toString(mana2));
                checkBtn.setEnabled(true);
                answerET.setText("");
            }
        });
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answer == Integer.parseInt(answerET.getText().toString())){
                    scoreTV.setText(Integer.toString(score++));
                    checkBtn.setEnabled(false);
                }
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        showAllUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}