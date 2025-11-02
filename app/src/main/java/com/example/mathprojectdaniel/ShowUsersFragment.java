package com.example.mathprojectdaniel;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

public class ShowUsersFragment extends Fragment {

    private EditText editNameET;
    private TextView ratingTV;
    private TextView scoreTV;
    private Button addImageBtn;
    private Button addUserBtn;
    private ImageView profile;
    private Gson gson;
    private User user;
    private Uri uri;
    private ActivityResultLauncher<Intent> registeredListenerForImage;
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
        profile = viw.findViewById(R.id.image);
        gson = new Gson();
        user = gson.fromJson(getArguments().getString("user"), User.class);

        registeredListenerForImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                profile.setImageURI(uri);
            }
        });

        setUserData();
        setOnClickListeners();


        return viw;
    }
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

            }
        });
    }
}