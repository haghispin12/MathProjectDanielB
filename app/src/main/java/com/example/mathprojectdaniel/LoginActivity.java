package com.example.mathprojectdaniel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText inputET;
    private Button submitBtn;
    private Intent intent;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        inputET.setText(sp.getString("username", ""));
        setSPAsText();
        setOnClickListeners();
    }

    public void init(){
        inputET = findViewById(R.id.login);
        submitBtn = findViewById(R.id.submit);
        intent = new Intent(this, MainActivity.class);
        sp = getSharedPreferences("name", MODE_PRIVATE);
    }
    public void setOnClickListeners(){
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putString("username", inputET.getText().toString()).apply();
                intent.putExtra("username",inputET.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }
    public void setSPAsText(){
        inputET.setText(sp.getString("username",""));
    }
}