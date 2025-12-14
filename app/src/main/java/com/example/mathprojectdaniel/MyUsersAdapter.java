package com.example.mathprojectdaniel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyUsersAdapter extends RecyclerView.Adapter<MyUsersAdapter.MyViewHolder>{
    public interface OnItemClickListener {
        void onItemClick(User item);
    }
    public interface OnItemLongClickListener{
        void onItemLongClick(User item);
    }

    private ArrayList<User> users;
    private MyUsersAdapter.OnItemClickListener listener;
    private MyUsersAdapter.OnItemLongClickListener longClickListener;


    public MyUsersAdapter  (ArrayList<User> users, MyUsersAdapter.OnItemClickListener listener, MyUsersAdapter.OnItemLongClickListener longClickListener) {
        this.users = users;
        this.listener = listener;
        this.longClickListener = longClickListener;
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
        holder.bind(users.get(position), listener, longClickListener);
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

        private TextView nameTV;
        private TextView scoreTV;
        private ImageView profile;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.showUserName);
            scoreTV = itemView.findViewById(R.id.showUserScoreModule);
            profile = itemView.findViewById(R.id.imageModule);
        }

        public void bind(final User item, final MyUsersAdapter.OnItemClickListener listener, final MyUsersAdapter.OnItemLongClickListener longClickListener){
            nameTV.setText(item.getName());
            scoreTV.setText("" + item.getScore());
            profile.setImageBitmap(item.getProfileBitmap());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override public boolean onLongClick(View v){longClickListener.onItemLongClick(item); return false;}
            });
        }

    }//end inner class
}
