package com.example.mathprojectdaniel;

import static org.jetbrains.annotations.Nls.Capitalization.Title;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private Button editUserBtn;
    private Button deleteUserBtn;
    private ImageView profile;
    private RecyclerView RV;
    private Gson gson;
    private User myUser;
    private User selectedUser;
    private Uri uri;
    private ActivityResultLauncher<Intent> registeredListenerForImage;
    private AlertDialog.Builder alertDialog;
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
        editUserBtn = viw.findViewById(R.id.edit_user);
        editUserBtn.setVisibility(View.INVISIBLE);
        deleteUserBtn = viw.findViewById(R.id.delete_user);
        deleteUserBtn.setVisibility(View.INVISIBLE);
        profile = viw.findViewById(R.id.image);
        RV = viw.findViewById(R.id.user_recycle_view);
        gson = new Gson();
        myUser = gson.fromJson(getArguments().getString("user"), User.class);
        selectedUser = myUser;

        registeredListenerForImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                profile.setImageURI(uri);
                selectedUser.setProfile(uri);
            }
        });
        alertDialog = new AlertDialog.Builder(c);
        alertDialog.setTitle("Delete");
        alertDialog.setMessage("Do you want to delete?");



        setUserData(selectedUser);
        setOnClickListeners();
        startAdapter();
        return viw;
    }
    //public ShowUsersFragment(User user){this.user = user;}
    public void setUserData(User u){
        editNameET.setText(u.getName());
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
                SQLite.insert(myUser, c);
                startAdapter();
            }
        });
        editUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLite = new DBHelper(c);
                SQLite.update(selectedUser);

                startAdapter();

                selectedUser = myUser;
                setUserData(selectedUser);
                editUserBtn.setVisibility(View.GONE);
                deleteUserBtn.setVisibility(View.GONE);
                profile.setVisibility(View.VISIBLE);
                addUserBtn.setVisibility(View.VISIBLE);
                addImageBtn.setVisibility(View.VISIBLE);
            }
        });
        deleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });
        editNameET.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                selectedUser.setName("" + editNameET.getText());
            }
        });

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SQLite = new DBHelper(c);
                SQLite.deleteById(selectedUser.getId());

                startAdapter();

                selectedUser = myUser;
                setUserData(selectedUser);
                editUserBtn.setVisibility(View.GONE);
                deleteUserBtn.setVisibility(View.GONE);
                profile.setVisibility(View.VISIBLE);
                addUserBtn.setVisibility(View.VISIBLE);
                addImageBtn.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }
    public void startAdapter(){
        SQLite = new DBHelper(c);
        userList = SQLite.selectAll();

        RV.setLayoutManager(new LinearLayoutManager(c));
        RV.setHasFixedSize(true);
        RV.setAdapter(new MyUsersAdapter(userList, new MyUsersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User item) {
                Toast.makeText(c,item.getName(),Toast.LENGTH_SHORT).show();
            }
        }, new MyUsersAdapter.OnItemLongClickListener(){
            @Override
            public void onItemLongClick(User user){
                selectedUser = user;
                addUserBtn.setVisibility(View.GONE);
                addImageBtn.setVisibility(View.GONE);
                profile.setVisibility(View.GONE);
                editUserBtn.setVisibility(View.VISIBLE);
                deleteUserBtn.setVisibility(View.VISIBLE);
                setUserData(selectedUser);
            }
        }));
    }
}