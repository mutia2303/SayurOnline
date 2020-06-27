package com.mobile.mutia.sayuronline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mobile.mutia.sayuronline.model.ResponseRegister;
import com.mobile.mutia.sayuronline.network.ConfigRetrofit;
import com.mobile.mutia.sayuronline.storage.SharedPrefManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editUsername, editPassword, editNama, editNo_hp, editEmail, editAlamat;
    private String username, password, nama, no_hp, email, alamat, status;
    private RadioGroup rgStatus;
    private RadioButton pembeli, penjual, rbStatus;
    String selectedId;

    FirebaseAuth auth;
    DatabaseReference reference;
    private Button CreateAccountButton;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sayur Online");

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();


        editUsername = (EditText) findViewById(R.id.register_username);
        editPassword = (EditText) findViewById(R.id.register_password);
        editNama = (EditText) findViewById(R.id.register_name);
        editNo_hp = (EditText) findViewById(R.id.register_nohp);
        editEmail = (EditText) findViewById(R.id.register_email);
        editAlamat = (EditText) findViewById(R.id.register_alamat);
        rgStatus = (RadioGroup) findViewById(R.id.rgStatus);
        pembeli = (RadioButton) findViewById(R.id.rbPembeli);
        penjual = (RadioButton) findViewById(R.id.rbPenjual);

//        int selectedId = rgStatus.getCheckedRadioButtonId();

//        rbStatus = (RadioButton) findViewById(selectedId);

        findViewById(R.id.register_button).setOnClickListener(this);
        findViewById(R.id.text_login).setOnClickListener(this);

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
//            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
//    }

    private void userRegister() {

        username = editUsername.getText().toString().trim();
        password = editPassword.getText().toString().trim();
        nama = editNama.getText().toString().trim().toLowerCase();
        no_hp = editNo_hp.getText().toString().trim();
        email = editEmail.getText().toString().trim();
        alamat = editAlamat.getText().toString().trim();
//        status = rbStatus.getText().toString().trim();

        if (pembeli.isChecked()) {
            selectedId = "pembeli";
        } else if (penjual.isChecked()) {
            selectedId = "penjual";
        }

        if (password.isEmpty()) {
            editPassword.setError("Password is required");
            editPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editPassword.setError("Password should be atleast 6 character long");
            editPassword.requestFocus();
            return;
        }

        if (username.isEmpty()) {
            editUsername.setError("Username is required");
            editUsername.requestFocus();
            return;
        }

        if (username.length() < 6) {
            editUsername.setError("Username should be atleast 6 character long");
            editUsername.requestFocus();
            return;
        }

        if (nama.isEmpty()) {
            editNama.setError("Name is required");
            editNama.requestFocus();
            return;
        }

        if (no_hp.isEmpty()) {
            editNo_hp.setError("Handphone is required");
            editNo_hp.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editEmail.setError("Email is required");
            editEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Enter a valid email");
            editEmail.requestFocus();
            return;
        }

        if (alamat.isEmpty()) {
            editAlamat.setError("Address is required");
            editAlamat.requestFocus();
            return;
        }

        ConfigRetrofit.getInstance().getApi().insertRegister(username,password,nama,no_hp,email,alamat,selectedId).enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {

                if(response.isSuccessful()){
                    String pesan = response.body().getPesan();
                    int data = response.body().getData();

                    if(data == 1){

                        Toast.makeText(RegisterActivity.this, pesan, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

//                            auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
                                    FirebaseUser firebaseUser = auth.getCurrentUser();
                                    assert firebaseUser != null;
                                    String userId = firebaseUser.getUid();

                                    reference = FirebaseDatabase.getInstance().getReference("Users").child(userId);
                                    //reference.child("Users").child(currentUserId).setValue("");

                                    HashMap<String, String> hashMap = new HashMap<>();
                                    hashMap.put("id", userId);
                                    hashMap.put("username", username);
                                    hashMap.put("nama", nama.toLowerCase());
                                    hashMap.put("no_hp", no_hp);
                                    hashMap.put("alamat", alamat);
                                    hashMap.put("password", password);
                                    hashMap.put("email", email);
                                    hashMap.put("status", selectedId);

                                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
//                                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                                startActivity(intent);
//                                                finish();
                                            }
                                        }
                                    });
                                }
//                            });
//                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_button:
                userRegister();
                break;
            case R.id.text_login:
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }


}
