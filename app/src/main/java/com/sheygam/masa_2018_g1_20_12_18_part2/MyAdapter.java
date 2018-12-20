package com.sheygam.masa_2018_g1_20_12_18_part2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<User> users = new ArrayList<>();
    private MyClickListener listener;

    public MyAdapter() {
        for (int i = 0; i < 100; i++) {
            users.add(new User("User " + i,"user"+i+"@mail.com"));
        }
    }

    public void setListener(MyClickListener listener) {
        this.listener = listener;
    }

    public void add(User user, int position){
        users.add(position,user);
        notifyItemInserted(position);
    }

    public void remove(int position){
        users.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MY_TAG", "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d("MY_TAG", "onBindViewHolder: " + position);
        User user = users.get(position);
        holder.emailTxt.setText(user.email);
        holder.nameTxt.setText(user.name);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameTxt, emailTxt;
        public MyViewHolder(View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.nameTxt);
            emailTxt = itemView.findViewById(R.id.emailTxt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onClick(getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(listener!= null){
                        listener.onLongClick(getAdapterPosition());
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    public interface MyClickListener{
        void onClick(int pos);
        void onLongClick(int pos);
    }
}
