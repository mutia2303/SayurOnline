package com.mobile.mutia.sayuronline;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mobile.mutia.sayuronline.adapter.AdapterUser;
import com.mobile.mutia.sayuronline.model.Chat;
import com.mobile.mutia.sayuronline.model.Pengguna;
import com.mobile.mutia.sayuronline.model.User;
import com.mobile.mutia.sayuronline.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class UserActivity extends AppCompatActivity {

    private DatabaseReference reference;
    private FirebaseUser firebaseUser;
    private RecyclerView recyclerViewUser;

    private AdapterUser adapterUser;
    private List<User> users;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pengguna");

        progressBar = findViewById(R.id.progress);
//        progressBar.setVisibility(View.GONE);

        recyclerViewUser = findViewById(R.id.recyclerViewUser);
        recyclerViewUser.setHasFixedSize(true);
        recyclerViewUser.setLayoutManager(new LinearLayoutManager(UserActivity.this));
        recyclerViewUser.setItemAnimator(new DefaultItemAnimator());

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference();

        users = new ArrayList<>();

        reference.child("Users").orderByChild("nama").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.clear();
                progressBar.setVisibility(View.GONE);

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    //chat.setId(dataSnapshot.getKey());
                    assert user != null;
                    assert firebaseUser != null;
                    if (!user.getId().equals(firebaseUser.getUid())) {

                        User user1 = SharedPrefManager.getInstance(UserActivity.this).getUserFB();

                        if (user.getStatus().equals("pembeli")) {
//                            String status = "pembeli";
                            if (user1.getStatus().equals("penjual")) {
                                users.add(user);
                            }

                        } else {
//                            String status = "penjual";
                            if (user.getStatus().equals("pembeli")) {
                                users.add(user);
                            }
                        }
                    }
                }

                adapterUser = new AdapterUser(UserActivity.this, users);
                recyclerViewUser.setAdapter(adapterUser);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
