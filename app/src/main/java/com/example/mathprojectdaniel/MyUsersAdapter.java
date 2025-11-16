package com.example.mathprojectdaniel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyUsersAdapter extends RecyclerView.Adapter<MyUsersAdapter.MyViewHolder>{
    public interface OnItemClickListener {
        void onItemClick(User item);
    }

    private ArrayList<User> users;
    private MyUsersAdapter.OnItemClickListener listener;


    public MyUsersAdapter  (ArrayList<User> users, MyUsersAdapter.OnItemClickListener listener) {
        this.users = users;
        this.listener = listener;
    }

    //
    @NonNull
    @Override
    public MyUsersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_user, parent, false);

        return new MyUsersAdapter.MyViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull MyUsersAdapter.MyViewHolder holder, int position) {
        holder.bind(users.get(position), listener);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return users.size();
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private EditText editNameET;
        private TextView ratingTV;
        private TextView scoreTV;
        private Button addImageBtn;
        private Button addUserBtn;
        private ImageView profile;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            editNameET = itemView.findViewById(R.id.editUserName);
            ratingTV = itemView.findViewById(R.id.showUserRating);
            scoreTV = itemView.findViewById(R.id.showUserScore);
            addImageBtn = itemView.findViewById(R.id.addImage);
            addUserBtn = itemView.findViewById(R.id.addUser);
            profile = itemView.findViewById(R.id.image);
        }

        public void bind(final User item, final MyUsersAdapter.OnItemClickListener listener){
            editNameET.setText(item.getName());
            ratingTV.setText("" + item.getRating());
            scoreTV.setText("" + item.getScore());
            profile.setImageURI(item.getProfile());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }//end inner class
}
