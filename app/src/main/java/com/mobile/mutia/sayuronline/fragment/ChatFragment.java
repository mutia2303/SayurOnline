package com.mobile.mutia.sayuronline.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.mobile.mutia.sayuronline.MainActivity;
import com.mobile.mutia.sayuronline.R;
import com.mobile.mutia.sayuronline.adapter.AdapterUser;
import com.mobile.mutia.sayuronline.model.Chat;
import com.mobile.mutia.sayuronline.model.Chatlist;
import com.mobile.mutia.sayuronline.model.User;
import com.mobile.mutia.sayuronline.notification.Token;
import com.mobile.mutia.sayuronline.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    private RecyclerView recyclerView;

    private AdapterUser adapterUser;
    private List<User> users;

    FirebaseUser firebaseUser;
    DatabaseReference reference;

    private List<Chatlist> userList;

    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerView = view.findViewById(R.id.recycle_chat);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressBar = view.findViewById(R.id.progress);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null
                && SharedPrefManager.getInstance(getActivity()).isLoggedIn()) {

            userList = new ArrayList<>(); //data dari model Chat

            reference = FirebaseDatabase.getInstance().getReference("Chatlist").child(firebaseUser.getUid());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    userList.clear();
                    progressBar.setVisibility(View.GONE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Chatlist chatlist = snapshot.getValue(Chatlist.class);

                        userList.add(chatlist);
                    }

                    chatList();

                    updateToken(FirebaseInstanceId.getInstance().getToken());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        return view;
    }

    private void updateToken(String token) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tokens");
        Token token1 = new Token(token);
        reference.child(firebaseUser.getUid()).setValue(token1);
    }

    private void chatList() {

        users = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);

                    for (Chatlist chatlist : userList) {
                        if (user.getId().equals(chatlist.getId())) {
                            users.add(user);
                        }
                    }
                }

                adapterUser = new AdapterUser(getContext(), users);
                recyclerView.setAdapter(adapterUser);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
