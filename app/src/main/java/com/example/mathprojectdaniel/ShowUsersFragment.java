package com.example.mathprojectdaniel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowUsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowUsersFragment extends Fragment {
    TextView score;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viw=inflater.inflate(R.layout.fragment_showusers, container, false);
        score = viw.findViewById();



        return viw;
    }
}