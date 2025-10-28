package com.example.mathprojectdaniel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowUsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowUsersFragment extends Fragment {

    EditText editNameET;
    TextView ratingTV;
    TextView scoreTV;
    Button addImageBtn;
    Button addUserBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viw=inflater.inflate(R.layout.fragment_showusers, container, false);
        editNameET = viw.findViewById(R.id.editUserName);
        ratingTV = viw.findViewById(R.id.showUserRating);
        scoreTV = viw.findViewById(R.id.showUserScore);
        addImageBtn = viw.findViewById(R.id.addImage);
        addUserBtn = viw.findViewById(R.id.addUser);
        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        return viw;
    }
}