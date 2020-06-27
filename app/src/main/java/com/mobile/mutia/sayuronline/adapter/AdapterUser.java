package com.mobile.mutia.sayuronline.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.mutia.sayuronline.MessageActivity;
import com.mobile.mutia.sayuronline.R;
import com.mobile.mutia.sayuronline.model.User;

import java.util.List;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.ViewHolderUser> {

    Context context;
    List<User> users;

    public AdapterUser(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public ViewHolderUser onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, viewGroup, false);
        ViewHolderUser vh = new ViewHolderUser(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderUser viewHolderUser, int position) {
        final User user = users.get(position);

        viewHolderUser.nama.setText(user.getNama());

        viewHolderUser.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessageActivity.class);
                intent.putExtra("userid", user.getId());
                intent.putExtra("nama", user.getNama());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolderUser extends RecyclerView.ViewHolder {

        TextView nama;

        public ViewHolderUser(@NonNull View itemView) {
            super(itemView);

            nama = itemView.findViewById(R.id.namaChat);
        }
    }
}
