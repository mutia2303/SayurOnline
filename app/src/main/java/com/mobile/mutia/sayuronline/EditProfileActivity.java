package com.mobile.mutia.sayuronline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mobile.mutia.sayuronline.fragment.ProfileFragment;
import com.mobile.mutia.sayuronline.model.Pengguna;
import com.mobile.mutia.sayuronline.model.ResponseProfile;
import com.mobile.mutia.sayuronline.model.User;
import com.mobile.mutia.sayuronline.network.ConfigRetrofit;
import com.mobile.mutia.sayuronline.storage.SharedPrefManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    private EditText editUsername, editNama, editNo_hp, editAlamat;
    private Button btn_edit_profile;
    private String id_pengguna, username, nama, no_hp, email, alamat;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Profile");

        editUsername = findViewById(R.id.editUsername);
        editNama = findViewById(R.id.editNama);
        editNo_hp = findViewById(R.id.editNo_hp);
        editAlamat = findViewById(R.id.editAlamat);

        editUsername.setText(getIntent().getStringExtra("username"));
        editNama.setText(getIntent().getStringExtra("nama"));
        editNo_hp.setText(getIntent().getStringExtra("no_hp"));
        editAlamat.setText(getIntent().getStringExtra("alamat"));
    
        btn_edit_profile = findViewById(R.id.btn_edit_profile);
        btn_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile();
            }
        });
    }

    private void profile() {
        Pengguna pengguna = SharedPrefManager.getInstance(EditProfileActivity.this).getUser();

        username = editUsername.getText().toString().trim();
        nama = editNama.getText().toString().trim();
        no_hp = editNo_hp.getText().toString().trim();
        alamat = editAlamat.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(nama) || TextUtils.isEmpty(no_hp)
                || TextUtils.isEmpty(alamat)) {
            Toast.makeText(this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
        } else {
            ConfigRetrofit.getInstance().getApi().updateProfile(pengguna.getId_pengguna(), username, nama, no_hp, alamat).enqueue(new Callback<ResponseProfile>() {
                @Override
                public void onResponse(Call<ResponseProfile> call, Response<ResponseProfile> response) {
                    if (response.isSuccessful()) {
                        String pesan = response.body().getPesan();
                        int sukses = response.body().getSukses();

                        if (sukses == 1) {

                            Toast.makeText(EditProfileActivity.this, pesan, Toast.LENGTH_SHORT).show();

                            SharedPrefManager.getInstance(EditProfileActivity.this).saveUser(response.body().getPengguna());
                            Intent intent = new Intent(EditProfileActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(EditProfileActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseProfile> call, Throwable t) {

                }
            });

            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);

                    editUsername.setText(user.getUsername());
                    editNama.setText(user.getNama());
                    editAlamat.setText(user.getAlamat());
                    editNo_hp.setText(user.getNo_hp());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("username", username);
            hashMap.put("nama", nama);
            hashMap.put("no_hp", no_hp);
            hashMap.put("alamat", alamat);

            reference.updateChildren(hashMap);
        }

    }
}
