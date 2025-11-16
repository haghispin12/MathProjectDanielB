package com.example.mathprojectdaniel;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowAllFruits extends AppCompatActivity {
    private RecyclerView showFruitsRV;
    private ArrayList<Fruit> fruits=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_fruits);

        showFruitsRV = findViewById(R.id.ShowFruits);

        fruits.add(new Fruit("banana",R.drawable.banana));
        fruits.add(new Fruit("apple",R.drawable.apple));
        fruits.add(new Fruit("orange",R.drawable.orange));
        fruits.add(new Fruit("grapes",R.drawable.grapes));
        fruits.add(new Fruit("lemon",R.drawable.lemon));

        MyFruitsAdapter  myFruitsAdapter = new MyFruitsAdapter(fruits, new MyFruitsAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Fruit item) {
                Toast.makeText(ShowAllFruits.this,item.getName(),Toast.LENGTH_SHORT).show();
            }
        });

        showFruitsRV.setLayoutManager(new LinearLayoutManager(this));
        showFruitsRV.setAdapter(myFruitsAdapter);
        showFruitsRV.setHasFixedSize(true);
    }
}