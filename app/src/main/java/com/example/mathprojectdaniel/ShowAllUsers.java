package com.example.mathprojectdaniel;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ShowAllUsers extends Fragment {

    private EditText editNameET;
    private TextView ratingTV;
    private TextView scoreTV;
    private Button addImageBtn;
    private Button addUserBtn;
    private ImageView profile;
    private RecyclerView RV;
    private Gson gson;
    private User user;
    private Uri uri;
    private ActivityResultLauncher<Intent> registeredListenerForImage;
    private DBHelper SQLite;
    private ArrayList<User> userList;
    private Context c;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viw=inflater.inflate(R.layout.show_all_users, container, false);
        c = viw.getContext();
        editNameET = viw.findViewById(R.id.editUserName);
        ratingTV = viw.findViewById(R.id.showUserRating);
        scoreTV = viw.findViewById(R.id.showUserScore);
        addImageBtn = viw.findViewById(R.id.addImage);
        addUserBtn = viw.findViewById(R.id.addUser);
        profile = viw.findViewById(R.id.image);
        SQLite = new DBHelper(c);
        RV = viw.findViewById(R.id.user_recycle_view);
        userList = SQLite.selectAll();
        gson = new Gson();
        user = gson.fromJson(getArguments().getString("user"), User.class);

        registeredListenerForImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                profile.setImageURI(uri);
                user.setProfile(uri);
            }
        });

        setUserData();
        setOnClickListeners();
        RV.setLayoutManager(new LinearLayoutManager(c));
        RV.setAdapter(new MyUsersAdapter(userList, new MyUsersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User item) {
                Toast.makeText(c,item.getName(),Toast.LENGTH_SHORT).show();
            }
        }));
        RV.setHasFixedSize(true);

        return viw;
    }
    //public ShowUsersFragment(User user){this.user = user;}
    public void setUserData(){
        editNameET.setText(user.getName());
        ratingTV.setText("" + user.getRating());
        scoreTV.setText("" + user.getScore());
    }
    public void setOnClickListeners(){
        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
                uri = requireContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                registeredListenerForImage.launch(cameraIntent);
            }
        });
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLite = new DBHelper(c);
                SQLite.insert(user, c);
                userList = SQLite.selectAll();
            }
        });
    }
}