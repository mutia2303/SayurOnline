package com.mobile.mutia.sayuronline.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobile.mutia.sayuronline.R;
import com.mobile.mutia.sayuronline.model.Chat;

import java.util.List;

public class AdapterMessage extends RecyclerView.Adapter<AdapterMessage.ViewHolderMessage> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    Context context;
    List<Chat> chats;

    FirebaseUser firebaseUser;

    public AdapterMessage(Context context, List<Chat> chats) {
        this.context = context;
        this.chats = chats;
    }

    @NonNull
    @Override
    public ViewHolderMessage onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_right, viewGroup, false);
            return new ViewHolderMessage(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_left, viewGroup, false);
            return new ViewHolderMessage(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMessage.ViewHolderMessage viewHolderMessage, int position) {
        Chat chat = chats.get(position);

        viewHolderMessage.showMessage.setText(chat.getMessage());


    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class ViewHolderMessage extends RecyclerView.ViewHolder {

        TextView showMessage;

        public ViewHolderMessage(@NonNull View itemView) {
            super(itemView);

            showMessage = itemView.findViewById(R.id.show_message);
        }
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (chats.get(position).getSender().equals(firebaseUser.getUid())) {
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }

    }
}
