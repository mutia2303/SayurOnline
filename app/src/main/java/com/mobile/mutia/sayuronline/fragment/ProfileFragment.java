package com.mobile.mutia.sayuronline.fragment;


import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mobile.mutia.sayuronline.EditProfileActivity;
import com.mobile.mutia.sayuronline.LoginActivity;
import com.mobile.mutia.sayuronline.MainActivity;
import com.mobile.mutia.sayuronline.R;
import com.mobile.mutia.sayuronline.SayurDetailActivity;
import com.mobile.mutia.sayuronline.TransaksiActivity;
import com.mobile.mutia.sayuronline.model.Pengguna;
import com.mobile.mutia.sayuronline.model.Profile;
import com.mobile.mutia.sayuronline.model.ResponsePassword;
import com.mobile.mutia.sayuronline.model.ResponseProfile;
import com.mobile.mutia.sayuronline.model.User;
import com.mobile.mutia.sayuronline.network.ConfigRetrofit;
import com.mobile.mutia.sayuronline.storage.SharedPrefManager;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView textUsername, textNama, textNo_hp, textEmail, textAlamat, textStatus;
    private Button btn_profile, btn_password, btn_transaksi;

    private EditText editPassword;
    private String password;

    FirebaseAuth firebaseAuth;

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        textUsername = view.findViewById(R.id.textUsername);
        textNama = view.findViewById(R.id.textNama);
        textNo_hp = view.findViewById(R.id.textNo_hp);
        textEmail = view.findViewById(R.id.textEmail);
        textAlamat = view.findViewById(R.id.textAlamat);
        textStatus = view.findViewById(R.id.textStatus);

        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        final Pengguna pengguna = SharedPrefManager.getInstance(getActivity()).getUser();

        textUsername.setText(pengguna.getUsername());
        textNama.setText(pengguna.getNama());
        textNo_hp.setText(pengguna.getNo_hp());
        textEmail.setText(pengguna.getEmail());
        textAlamat.setText(pengguna.getAlamat());
        textStatus.setText(pengguna.getStatus());

        btn_profile = view.findViewById(R.id.btn_profile);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                intent.putExtra("id_pengguna", pengguna.getId_pengguna());
                intent.putExtra("username", pengguna.getUsername());
                intent.putExtra("nama", pengguna.getNama());
                intent.putExtra("no_hp", pengguna.getNo_hp());
                intent.putExtra("email", pengguna.getEmail());
                intent.putExtra("alamat", pengguna.getAlamat());
                startActivity(intent);
            }
        });

        btn_password = view.findViewById(R.id.btn_password);
        btn_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ubah_password();

            }
        });

        btn_transaksi = view.findViewById(R.id.btn_transaksi);
        btn_transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TransaksiActivity.class);
                startActivity(intent);
            }
        });

        if (firebaseUser != null
                && SharedPrefManager.getInstance(getActivity()).isLoggedIn()) {
            btn_profile.setVisibility(View.VISIBLE);
            btn_password.setVisibility(View.VISIBLE);
            btn_transaksi.setVisibility(View.VISIBLE);

        } else {
            btn_profile.setVisibility(View.GONE);
            btn_password.setVisibility(View.GONE);
            btn_transaksi.setVisibility(View.GONE);
        }
    }

    private void ubah_password() {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_ubah_password, null);

        editPassword = view.findViewById(R.id.editPassword);

        AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();

        dialog.setView(view);
        dialog.setCancelable(false);
        dialog.setTitle("Ubah Password");

        dialog.setButton(AlertDialog.BUTTON_POSITIVE,"Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkInput();
            }
        });

        dialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();

        Button pbutton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
//        pbutton.setBackgroundColor(Color.BLUE);
        pbutton.setTextColor(Color.BLUE);

        Button nbutton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(Color.BLUE);
    }

    private void checkInput() {

        password = editPassword.getText().toString();

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            editPassword.setError("Password should be atleast 6 character long");
            editPassword.requestFocus();
            return;
        } else {
            proses_ubah_password();
        }
    }

    private void proses_ubah_password() {

        Pengguna pengguna = SharedPrefManager.getInstance(getActivity()).getUser();

        ConfigRetrofit.getInstance().getApi().ubahPassword(pengguna.getId_pengguna(), password).enqueue(new Callback<ResponsePassword>() {
            @Override
            public void onResponse(Call<ResponsePassword> call, Response<ResponsePassword> response) {
                if (response.isSuccessful()) {
                    String pesan  = response.body().getPesan();
                    int sukses = response.body().getSukses();

                    if (sukses == 1) {
                        Toast.makeText(getActivity(), pesan, Toast.LENGTH_SHORT).show();

                        SharedPrefManager.getInstance(getActivity()).saveUser(response.body().getPengguna());
                        FirebaseAuth.getInstance().signOut();

                        SharedPrefManager.getInstance(getActivity()).clear();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), pesan, Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponsePassword> call, Throwable t) {

            }
        });

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                editPassword.setText(user.getPassword());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("password", password);

        reference.updateChildren(hashMap);
    }
}
