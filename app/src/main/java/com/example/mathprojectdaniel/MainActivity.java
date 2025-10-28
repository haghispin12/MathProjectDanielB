package com.example.mathprojectdaniel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity{

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
    private Button rateBtn;
    private ActivityResultLauncher<Intent> registeredListener;
    protected ExerciseListener exerciseListener;
    protected Exercise exercise;
    protected Toast toaster;
    private User user;



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
        welcomeUser();
        CreateOnClickListeners();
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
        rateBtn = findViewById(R.id.openRate);
        registeredListener = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                float rating = result.getData().getFloatExtra("rating", -1);
                if(rating != -1)
                    toaster.setText("Thanks for the " + rating + " rating!");
                else
                    toaster.setText("error receiving rating");
                toaster.show();
            }
        });
        user = new User(getIntent().getStringExtra("username"));

        exerciseListener = new ExerciseListener() {
            @Override
            public void setManas(int mana1, int mana2) {
                mana1TV.setText(mana1 + "");
                mana2TV.setText(mana2 + "");
                checkBtn.setEnabled(true);
            }
            @Override
            public void correct(int current){
                scoreTV.setText(current+"");
                checkBtn.setEnabled(false);
                toaster.setText("Correct!");
                toaster.show();
            }
            public void incorrect(){
                toaster.setText("Wrong... Try Again");
                toaster.show();
            }
        };
        exercise = new Exercise(exerciseListener, user);
        toaster = new Toast(this);
        toaster.setDuration(Toast.LENGTH_SHORT);
    }
    public void startRate(){
        Intent intent = new Intent(this, RateActivity.class);
        registeredListener.launch(intent);
    }

    public void CreateOnClickListeners(){
        easyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exercise.easy();
            }
        });
        normalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exercise.normal();
            }
        });
        hardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exercise.hard();
            }
        });
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exercise.check(Integer.parseInt(answerET.getText().toString()));
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
                Bundle b = new Bundle();
                Gson gson = new Gson();
                String  stringUser = gson.toJson(user);
                b.putString("user", stringUser);
                ShowUsersFragment SUF = new ShowUsersFragment();
                SUF.setArguments(b);

                getSupportFragmentManager().beginTransaction().add(R.id.main, SUF).commit();
            }
        });
        rateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRate();
            }
        });
    }
    public void welcomeUser(){
        toaster.setText("Welcome, " + getIntent().getStringExtra("username"));
        toaster.show();
    }
    public int getUserScore(){return user.getScore();}
}